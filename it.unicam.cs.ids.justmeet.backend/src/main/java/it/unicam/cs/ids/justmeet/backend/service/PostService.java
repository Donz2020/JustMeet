package it.unicam.cs.ids.justmeet.backend.service;

import com.mongodb.DBRef;
import it.unicam.cs.ids.justmeet.backend.model.Location;
import it.unicam.cs.ids.justmeet.backend.model.Post;
import it.unicam.cs.ids.justmeet.backend.model.PostDescription;
import it.unicam.cs.ids.justmeet.backend.repository.LocationRepository;
import it.unicam.cs.ids.justmeet.backend.repository.PostDescriptionRepository;
import it.unicam.cs.ids.justmeet.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PostService")
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PostDescriptionRepository postDescriptionRepository;

    @Transactional
    public void savePost(Post post, Location location, PostDescription postDescription) {
        postDescriptionRepository.save(postDescription);

        post.setDescription(postDescriptionRepository.findById(postDescription.getId()).get());

        savePost(post, location);
    }

    @Transactional
    public void savePost(Post post, Location location) {
        locationRepository.save(location);

        post.setPostLocation(locationRepository.findById(location.getId()).get());

        postRepository.save(post);
    }

}
