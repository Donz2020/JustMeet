package it.unicam.cs.ids.justmeet.backend.payload.request;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.PostCategory;

import java.time.LocalDate;

public class PostRequest {
    String title;
    LocalDate date;
    double latitude;
    double longitude;
    String descriptionText;
    boolean descriptionFree;
    PostCategory descriptionType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public boolean isDescriptionFree() {
        return descriptionFree;
    }

    public void setDescriptionFree(boolean descriptionFree) {
        this.descriptionFree = descriptionFree;
    }

    public PostCategory getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(PostCategory descriptionType) {
        this.descriptionType = descriptionType;
    }
}
