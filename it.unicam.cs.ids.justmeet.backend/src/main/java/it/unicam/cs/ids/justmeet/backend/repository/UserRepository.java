package it.unicam.cs.ids.justmeet.backend.repository;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<IUser,String> {

    //boolean existsByUniqueID(String uniqueID);
}
