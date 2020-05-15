package it.unicam.ids.justmeet.service;


import it.unicam.ids.justmeet.model.User;
import it.unicam.ids.justmeet.repository.RoleRepository;
import it.unicam.ids.justmeet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRespository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public void saveUser(User user) {

       /* user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);

        Role userRole = roleRespository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);*/
    }


}