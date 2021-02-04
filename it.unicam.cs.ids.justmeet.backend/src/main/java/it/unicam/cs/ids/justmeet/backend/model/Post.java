package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;

@Document(collection = "posts")
public class Post {

    @Transient
    public static final String SEQUENCE_NAME = "posts_sequence";

    @Id
    private long id;

    @NotNull
    @DBRef
    private IUser owner;

    @DBRef
    private HashSet<IPhysicalUser> subscribers = new HashSet<>();

    @NotNull
    @DBRef
    private Location postLocation;

    @NotNull
    private LocalDate postDate;

    @NotNull
    private String postTitle;

    @NotNull
    @DBRef
    private PostDescription description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IUser getOwner() {
        return owner;
    }

    public void setOwner(IUser owner) {
        this.owner = owner;
    }

    public void setPostLocation(Location postLocation) {
        this.postLocation = postLocation;
    }

    public Location getPostLocation() {
        return postLocation;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public PostDescription getDescription() {
        return description;
    }

    public void setDescription(PostDescription description) {
        this.description = description;
    }

    public void addSubscriber(IPhysicalUser physicalUser) {
        subscribers.add(physicalUser);
    }

    public void removeSubscriber(IPhysicalUser physicalUser) {
        subscribers.remove(physicalUser);
    }

    public HashSet<IPhysicalUser> getSubscribers() {return subscribers; }
}
