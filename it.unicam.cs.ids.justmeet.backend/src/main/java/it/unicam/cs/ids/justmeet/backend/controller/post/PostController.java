package it.unicam.cs.ids.justmeet.backend.controller.post;


import it.unicam.cs.ids.justmeet.backend.repository.LocationRepository;
import it.unicam.cs.ids.justmeet.backend.service.SequenceGeneratorService;
import it.unicam.cs.ids.justmeet.backend.model.Location;
import it.unicam.cs.ids.justmeet.backend.model.Post;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.PostRepository;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import it.unicam.cs.ids.justmeet.backend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserDetailsServiceImpl userRepository;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    private IUser findUser(String name) {
        return userRepository.getUserInstance(name);
    }

    @PostMapping(path ="/demo")
    public ResponseEntity<?> editUserStatus() {

        Post post = new Post();

        post.setOwner(findUser("user@user.com"));

        post.setPostDate(LocalDate.now());

        Location location = new Location();

        location.setLatitude(111);
        location.setLongitude(21121);
        location.setId(sequenceGenerator.generateSequence(Location.SEQUENCE_NAME));

        post.setPostLocation(location);

        post.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));

        locationRepository.save(location);

        postRepository.save(post);

        return ResponseEntity.ok("fatto");
    }
}
