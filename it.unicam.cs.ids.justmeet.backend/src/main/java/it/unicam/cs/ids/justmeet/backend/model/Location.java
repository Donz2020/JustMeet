package it.unicam.cs.ids.justmeet.backend.model;

public class Location {

    private float latitude;

    private float longitude;

    private String name;

    public float[] getCoordinates(){
        return new float[]{latitude,longitude};
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

