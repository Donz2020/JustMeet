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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    private boolean replacePost(Post post)  {
        if(getPostById(post.getId()) != null) {
            deletePost(post.getId());
            postRepository.save(post);
            return true;
        }

        return false;
    }

    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void savePost(Post post, Location location, PostDescription postDescription) {
        postDescription.setId(sequenceGenerator.generateSequence(PostDescription.SEQUENCE_NAME));
        postDescriptionRepository.save(postDescription);

        post.setDescription(postDescriptionRepository.findById(postDescription.getId()).get());

        savePost(post, location);
    }

    @Transactional
    public void savePost(Post post, Location location) {
        location.setId(sequenceGenerator.generateSequence(Location.SEQUENCE_NAME));
        locationRepository.save(location);

        post.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));
        post.setPostLocation(locationRepository.findById(location.getId()).get());

        postRepository.save(post);
    }

    public boolean subscribePost(long postId, IPhysicalUser physicalUser) {
        Post post = getPostById(postId);
        post.addSubscriber(physicalUser);
        return replacePost(post);
    }

    public boolean unsubscribePost(long postId, IPhysicalUser physicalUser) {
        Post post = getPostById(postId);
        post.removeSubscriber(physicalUser);
        return replacePost(post);
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).get();
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    private List<Post> getPostsByPredicate(Function<Post, Boolean> p) {
        return postRepository.findAll().stream().filter(p::apply).collect(Collectors.toList());
    }

    public List<Post> getSubscribedPosts(IPhysicalUser user) {
        return getPostsByPredicate(x -> x.getSubscribers().contains(user));
    }

    public List<Post> getMyPosts(IUser user) {
        return getPostsByPredicate(x -> x.getOwner().equals(user));
    }

    public List<Post> getPostsByCategory(PostCategory cat) {
        return getPostsByPredicate(x -> x.getDescription().getType().equals(cat));
    }

}
