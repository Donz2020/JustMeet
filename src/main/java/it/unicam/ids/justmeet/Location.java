package it.unicam.ids.justmeet;

public class Location {
	
	float Latitude;
	
	float Longitude;
	
	String Name;
	
	public Location(float Latitude, float Longitude, String Name)
	{
		this.Latitude = Latitude;
		this.Longitude = Longitude;
		this.Name = Name;
	}
	
	public float[] GetCoordinates()
	{
		return new float[] { Latitude, Longitude};
	}

}
