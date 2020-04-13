package it.unicam.ids.justmeet;

/**
 * Ogni istanza di questa classe rappresenta un luogo
 * indicandolo con le sue cordinate.
 * @author Andrea
 *
 */
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
