package it.unicam.cs.ids.justmeet.backend.service;

import it.unicam.cs.ids.justmeet.backend.configuration.service.UserDetailsImpl;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
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

    private BiFunction<IUser, String, Boolean> comp = (u, username) -> u.getUsername().equals(username);

    private UserDetails build(IUser user) {
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public void saveUser(IUser user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(IUser user) {
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