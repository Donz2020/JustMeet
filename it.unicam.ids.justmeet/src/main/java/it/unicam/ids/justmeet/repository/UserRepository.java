package it.unicam.ids.justmeet.repository;


import it.unicam.ids.justmeet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    //UPGRADE AUTORITA'
    @Modifying
    @Query(value = "UPDATE user_role SET role_id = 3 WHERE user_id =?1",nativeQuery = true)
    Integer UpgradeDonatore(Integer id);



}