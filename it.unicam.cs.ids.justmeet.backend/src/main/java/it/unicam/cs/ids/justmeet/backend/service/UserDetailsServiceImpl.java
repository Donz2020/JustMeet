package it.unicam.cs.ids.justmeet.backend.service;

import it.unicam.cs.ids.justmeet.backend.configuration.service.UserDetailsImpl;
import it.unicam.cs.ids.justmeet.backend.model.User;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.BiFunction;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    private BiFunction<IUser, String, Boolean> comp = (u, username) -> u.getUsername().equals(username);

    private UserDetails build(IUser user) {
        return UserDetailsImpl.build(user);
    }

    //@Transactional
    public void saveUser(IUser user) {
        user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(IUser user) {

        if(Utils.isPhysicalUser(user)) {
            IPhysicalUser temp = (IPhysicalUser) user;
            postService.getSubscribedPosts(temp).forEach(x -> postService.unsubscribePost(x.getId(), temp));
        }

        postService.getMyPosts(user).forEach(x -> postService.deletePost(x.getId()));
        userRepository.delete(user);
    }

    @Transactional
    public boolean existByUsername(String username) {
        return userRepository.findAll().stream().anyMatch(u -> comp.apply(u, username));
    }

    @Transactional
    public IUser getUserInstance(String username) throws UsernameNotFoundException {
        return userRepository.findAll().stream().filter(u -> comp.apply(u, username)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User Not Found %s", username)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return build(getUserInstance(username));
    }

    @Transactional
    public void replaceUser(IUser user) throws UsernameNotFoundException {
        if(!existByUsername(user.getUsername()))
            throw new UsernameNotFoundException(String.format("User Not Found %s", user.getUsername()));
        deleteUser(user);
        saveUser(user);
    }
}
