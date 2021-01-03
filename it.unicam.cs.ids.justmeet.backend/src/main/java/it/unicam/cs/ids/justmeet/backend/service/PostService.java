package it.unicam.cs.ids.justmeet.backend.service;

import it.unicam.cs.ids.justmeet.backend.model.Location;
import it.unicam.cs.ids.justmeet.backend.model.Post;
import it.unicam.cs.ids.justmeet.backend.model.PostDescription;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.PostCategory;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.LocationRepository;
import it.unicam.cs.ids.justmeet.backend.repository.PostDescriptionRepository;
import it.unicam.cs.ids.justmeet.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Post getPostById(long id) {
        return postRepository.findById(id).get();
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    private List<Post> getPostsByPredicate(Object user, Function<Post, Boolean> p) {
        return postRepository.findAll().stream().filter(x -> p.apply(x)).collect(Collectors.toList());
    }

    public List<Post> getSubscribedPosts(IPhysicalUser user) {
        return getPostsByPredicate(user, (Post x) -> x.getSubscribers().contains(user));
    }

    public List<Post> getMyPosts(IUser user) {
        return getPostsByPredicate(user, (Post x) -> x.getOwner().equals(user));
    }

    public List<Post> getPostsByCategory(PostCategory cat) {
        return getPostsByPredicate(cat, (Post x) -> x.getDescription().getType().equals(cat));
    }

}
