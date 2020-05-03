package it.unicam.ids.justmeet.rest;

import java.util.Date;
import java.util.HashSet;

//TODO aggiungere metodo DeleteSubscribers()

/**
 * Ogni istanza di questa classe rappresenta un evento creato du un {@code IUser}.
 * @author Andrea
 *
 */
public class Post {

	IUser Owner;
	
	HashSet<Integer> Subscribers;
	
	Location PostLocation;
	
	Date PostDate;

	String PostTitle;
	
	PostDescription Description;
	
	public Post(IUser Owner, Location PostLocation, Date PostDate, String PostTitle, PostDescription Description)
	{
		this.Owner = Owner;
		this.PostLocation = PostLocation;
		this.PostDate = PostDate;
		this.PostTitle = PostTitle;
		this.Description = Description;
	}
	
	public boolean AddSubscribe(IPhysicalUser PhysicalUser)
	{
		return Subscribers.add(PhysicalUser.hashCode());
	}
	
	public HashSet<Integer> GetSubscribers()
	{
		return Subscribers;
	}
	
}
