package it.unicam.ids.justmeet.rest;

import java.util.Date;
import java.util.HashSet;


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
		if(Owner == null || PostLocation == null || PostDate == null || PostTitle == null || Description == null) throw new NullPointerException();
		this.Owner = Owner;
		this.PostLocation = PostLocation;
		this.PostDate = PostDate;
		this.PostTitle = PostTitle;
		this.Description = Description;
	}

	public IUser GetOwner(){
		return Owner;
	}

	public boolean AddSubscribe(IPhysicalUser PhysicalUser)
	{
		return Subscribers.add(PhysicalUser.hashCode());
	}
	
	public HashSet<Integer> GetSubscribers()
	{
		return Subscribers;
	}

	public boolean DeleteSubscriber(IPhysicalUser PhysicalUser) {
		return Subscribers.remove(PhysicalUser.hashCode());
	}

	public void DeleteSubscribers(){
		Subscribers.clear();
	}

	public Location GetLocation(){
		return PostLocation;
	}

	public Date GetDate(){
		return PostDate;
	}

	public String GetPostTitle(){
		return PostTitle;
	}

	public PostDescription GetDescription(){
		return Description;
	}

}

