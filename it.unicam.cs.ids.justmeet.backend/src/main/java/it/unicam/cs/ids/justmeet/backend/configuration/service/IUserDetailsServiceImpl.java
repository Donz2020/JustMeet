package it.unicam.cs.ids.justmeet.backend.configuration.service;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("IUserDetailsServiceImpl")
public class IUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private UserDetails build(IUser user) {
        return IUserDetailsImpl.build(user);
    }

    @Transactional
    public void saveUser(IUser user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(IUser user) {
        userRepository.save(user);
    }

    @Transactional
    public boolean existByUsername(String username) {
        return userRepository.findAll().stream().anyMatch(u -> u.getUsername().equals(username));
    }

    @Transactional
    public IUser getUserInstance(String username) throws UsernameNotFoundException {
        return userRepository.findAll().stream().filter( u -> u.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User Not Found %s",username)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IUser user = getUserInstance(username);
        return build(user);
    }

    @Transactional
    public void replaceUser(IUser user) throws UsernameNotFoundException {
        if(existByUsername(user.getUsername()))
            throw new UsernameNotFoundException(String.format("User Not Found %s",user.getUsername()));
        deleteUser(user);
        saveUser(user);
    }
}
