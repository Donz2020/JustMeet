package it.unicam.ids.justmeet.repository;


import it.unicam.ids.justmeet.model.User;
import it.unicam.ids.justmeet.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<UserRole,Integer> {
    //Role findByRole(String role);


    //@Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
}
