package it.unicam.ids.justmeet.service;


import it.unicam.ids.justmeet.model.User;


public interface UserService {

    User findUserByEmail(String email);
    void saveUser(User user);



}