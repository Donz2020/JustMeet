package it.unicam.cs.ids.justmeet.backend.payload.response;


import java.time.LocalDate;
import java.util.HashSet;

public class PostResponse {

    private long id;
    private String ownerName;
    private String postTitle;
    private float[] location;
    private LocalDate postDate;
    private String postDescription;
    private String postType;
    private HashSet<String> subscribers = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public float[] getLocation() {
        return location;
    }

    public void setLocation(float[] location) {
        this.location = location;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public HashSet<String> getSubscribers() {
        return subscribers;
    }

    public void addSubscribers(String username) {
        subscribers.add(username);
    }

}
