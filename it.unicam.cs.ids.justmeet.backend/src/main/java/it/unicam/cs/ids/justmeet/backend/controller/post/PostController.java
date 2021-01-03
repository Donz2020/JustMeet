package it.unicam.cs.ids.justmeet.backend.controller.post;


import it.unicam.cs.ids.justmeet.backend.model.PostDescription;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.PostCategory;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.payload.response.PostResponse;
import it.unicam.cs.ids.justmeet.backend.service.PostService;
import it.unicam.cs.ids.justmeet.backend.service.SequenceGeneratorService;
import it.unicam.cs.ids.justmeet.backend.model.Location;
import it.unicam.cs.ids.justmeet.backend.model.Post;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    private IUser findUser(String name) {
        return userService.getUserInstance(name);
    }

    @PostMapping(path ="/demo")
    public ResponseEntity<?> editUserStatus() {

        Post post = new Post();

        post.setOwner(findUser("user@user.com"));

        post.setPostDate(LocalDate.now());

        post.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));

        Location location = new Location();

        location.setLatitude(111);
        location.setLongitude(21121);
        location.setId(sequenceGenerator.generateSequence(Location.SEQUENCE_NAME));

        PostDescription postDescription = new PostDescription();

        postDescription.setType(PostCategory.FUN);

        postDescription.setFree(true);

        postDescription.setText("desc 1");

        postDescription.setId(sequenceGenerator.generateSequence(PostDescription.SEQUENCE_NAME));

        post.setPostTitle("demo post");

        post.addSubscriber((IPhysicalUser) findUser("staffolo@staffolo.it"));

        postService.savePost(post, location, postDescription);

        return ResponseEntity.ok("fatto");
    }

    private PostResponse newPostResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setOwnerName(post.getOwner().getUsername());
        postResponse.setPostTitle(post.getPostTitle());
        postResponse.setPostDate(post.getPostDate());
        postResponse.setLocation(post.getPostLocation().getCoordinates());
        postResponse.setPostDescription(post.getDescription().getText());
        postResponse.setPostType(post.getDescription().getType().getValue());
        if(!post.getSubscribers().isEmpty())
            post.getSubscribers().forEach(x -> postResponse.addSubscribers(x.getUsername()));
        return postResponse;
    }

    @GetMapping(path ="/getPost/{id}", produces = "application/json")
    public ResponseEntity<?> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(newPostResponse(postService.getPostById(id)));
    }

    @GetMapping(path ="/getPosts", produces = "application/json")
    public ResponseEntity<?> getPosts() {
        List<PostResponse> postResponseList = new ArrayList();
        postService.getPosts().forEach(x -> postResponseList.add(newPostResponse(x)));
        return ResponseEntity.ok(postResponseList);
    }
}
