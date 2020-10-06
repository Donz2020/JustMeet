package it.unicam.cs.ids.justmeet.backend.configuration.service;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("IUserDetailsServiceImpl")
public class IUserDetailsServiceImpl implements IUserDetailsService {

    @Autowired
    UserRepository userRepository;

    private UserDetails build(IUser user) {
        return IUserDetailsImpl.build(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUniqueID(String uniqueID) throws UsernameNotFoundException {
        IUser user = userRepository.findById(uniqueID)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User Not Found %s",uniqueID)));
            return build(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String uniqueID) throws UsernameNotFoundException {
        return loadUserByUniqueID(uniqueID);
    }

    @Override
    public UserDetails loadUserByUserInstance(IUser user) throws UsernameNotFoundException {
        return loadUserByUniqueID(user.getUniqueID());
    }
}
