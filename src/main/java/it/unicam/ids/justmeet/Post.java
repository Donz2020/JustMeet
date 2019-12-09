package it.unicam.ids.justmeet;

import java.util.Date;
import java.util.HashSet;

public class Post {

	IUser Owner;
	
	HashSet<User> Subscribers;
	
	Location PostLocation;
	
	Date PostDate;

	String PostTitle;
	
	PostDescription Description;
	
	public Post(User Owner, Location PostLocation, Date PostDate, String PostTitle, PostDescription Description)
	{
		this.Owner = Owner;
		this.PostLocation = PostLocation;
		this.PostDate = PostDate;
		this.PostTitle = PostTitle;
		this.Description = Description;
	}
	
}
