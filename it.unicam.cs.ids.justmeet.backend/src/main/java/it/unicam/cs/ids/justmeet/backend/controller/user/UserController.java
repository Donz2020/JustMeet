package it.unicam.cs.ids.justmeet.backend.controller.user;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    protected IUser findUser(String username) {
        return userDetailsServiceImpl.getUserInstance(username);
    }

    protected void replaceUser(IUser user) {
        userDetailsServiceImpl.replaceUser(user);
    }

    protected void deleteUser(IUser user) { userDetailsServiceImpl.deleteUser(user);}
}
