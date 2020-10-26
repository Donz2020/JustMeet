package it.unicam.cs.ids.justmeet.backend.repository;

import it.unicam.cs.ids.justmeet.backend.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, Long> {

}
