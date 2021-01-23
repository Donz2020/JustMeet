package it.unicam.cs.ids.justmeet.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "locations")
public class Location {

    @Transient
    public static final String SEQUENCE_NAME = "locations_sequence";

    @Id
    private long id;

    private double latitude;

    private double longitude;

    @NotNull
    @NotBlank
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double[] getCoordinates(){
        return new double[]{latitude,longitude};
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

