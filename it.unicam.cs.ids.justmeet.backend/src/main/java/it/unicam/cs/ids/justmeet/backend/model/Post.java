package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;

@Document
public class Post {


 @Id
 private IUser owner;

 private HashSet<Integer> subscribers;


 private Location postLocation;

 private Date postDate;

 private String postTitle;

 private PostDescription description;

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

 public Date getPostDate() {
  return postDate;
 }

 public void setPostDate(Date postDate) {
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
