package it.unicam.cs.ids.justmeet.backend.configuration.service;


import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUniqueID(String uniqueID) throws UsernameNotFoundException;

    UserDetails loadUserByUserInstance(IUser user) throws  UsernameNotFoundException;
}
