package it.unicam.cs.ids.justmeet.backend.controller.post;


import it.unicam.cs.ids.justmeet.backend.model.PostDescription;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.PostCategory;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.ActRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.PostRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.UserRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.PostResponse;
import it.unicam.cs.ids.justmeet.backend.service.PostService;
import it.unicam.cs.ids.justmeet.backend.service.SequenceGeneratorService;
import it.unicam.cs.ids.justmeet.backend.model.Location;
import it.unicam.cs.ids.justmeet.backend.model.Post;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.service.UserDetailsServiceImpl;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(path ="/add", consumes = "application/json")
    public ResponseEntity<?> add(@Valid @RequestBody PostRequest postRequest) {
        Post post = new Post();
        post.setOwner(findUser(Utils.getCurrentUser(SecurityContextHolder.getContext())));
        post.setPostTitle(postRequest.getTitle());

        Location location = new Location();
        location.setLatitude(postRequest.getLatitude());
        location.setLongitude(postRequest.getLongitude());

        PostDescription postDescription = new PostDescription();
        postDescription.setType(postRequest.getDescriptionType());
        postDescription.setFree(postRequest.isDescriptionFree());
        postDescription.setText(postRequest.getDescriptionText());

        post.setPostDate(postRequest.getDate());

        postService.savePost(post, location, postDescription);

        return ResponseEntity.ok("fatto");
    }

    @PostMapping(path ="/delete/{postId}")
    public ResponseEntity<?> delete(@PathVariable long postId) {
        IUser user = findUser(Utils.getCurrentUser(SecurityContextHolder.getContext()));
        if(postService.getPostById(postId).getOwner().equals(user))
            postService.deletePost(postId);

        return ResponseEntity.ok("fatto");
    }

    @PostMapping(path ="/delete/{postId}/subscriber")
    public ResponseEntity<?> deleteSubscriber(@PathVariable long postId) {
        IUser user = findUser(Utils.getCurrentUser(SecurityContextHolder.getContext()));
        IPhysicalUser physicalUser;

        if(Utils.isPhysicalUser(user))
            physicalUser = (IPhysicalUser) user;
        else
            return ResponseEntity.ok("utente non valido");

        return postService.unsubscribePost(postId, physicalUser) ? ResponseEntity.ok("fatto") : ResponseEntity.ok("male male");
    }

    @PostMapping(path ="/subscribe/{postId}", produces = "application/json")
    public ResponseEntity<?> addSubscriber(@PathVariable long postId) {
        IUser user = findUser(Utils.getCurrentUser(SecurityContextHolder.getContext()));
        IPhysicalUser physicalUser;

        if(Utils.isPhysicalUser(user))
            physicalUser = (IPhysicalUser) user;
        else
            return ResponseEntity.ok("utente non valido");

        return postService.subscribePost(postId, physicalUser) ?  ResponseEntity.ok("ciao") : ResponseEntity.ok("male");
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

    @GetMapping(path ="/getPost/{id}/owner", produces = "application/json")
    public ResponseEntity<?> getPostOwnerById(@PathVariable long id) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(postService.getPostById(id).getOwner().getUsername());
        return ResponseEntity.ok(userRequest);
    }

    @GetMapping(path ="/getPosts", produces = "application/json")
    public ResponseEntity<?> getPosts() {
        List<PostResponse> postResponseList = new ArrayList();
        postService.getPosts().forEach(x -> postResponseList.add(newPostResponse(x)));
        return ResponseEntity.ok(postResponseList);
    }

    @GetMapping(path ="/getMyPosts", produces = "application/json")
    public ResponseEntity<?> getMyPosts() {
        List<PostResponse> postResponseList = new ArrayList();
        postService.getMyPosts(findUser(Utils.getCurrentUser(SecurityContextHolder.getContext()))).forEach(x -> postResponseList.add(newPostResponse(x)));
        return ResponseEntity.ok(postResponseList);
    }
}
