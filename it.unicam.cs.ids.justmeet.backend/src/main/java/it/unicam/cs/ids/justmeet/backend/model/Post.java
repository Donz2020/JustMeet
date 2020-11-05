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

 private int ownerHashCode;

 private HashSet<Integer> subscribers;

 @NotNull
 @DBRef
 private Location postLocation;

 @NotNull
 private LocalDate postDate;

 private String postTitle;

 private PostDescription description;

 public long getId() {
  return id;
 }

 public void setId(long id) {
  this.id = id;
 }

 public int getOwnerHashCode() {
  return ownerHashCode;
 }

 public void setOwner(IUser ownerHashCode) {
  this.ownerHashCode = ownerHashCode.hashCode();
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

 public void addSubscriber(IPhysicalUser physicalUser){
  subscribers.add(physicalUser.hashCode());
 }

}
