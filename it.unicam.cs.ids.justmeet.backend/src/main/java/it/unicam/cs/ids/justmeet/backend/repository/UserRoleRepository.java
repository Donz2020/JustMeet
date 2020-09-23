package it.unicam.cs.ids.justmeet.backend.repository;

import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    Optional<UserRole> findByName(String s);
}