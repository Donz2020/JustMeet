package it.unicam.cs.ids.justmeet.backend.payload.response;

import java.util.List;

public class DetailsResponse {

    String username;

    String details;

    List<String> roles;

    String name;

    public DetailsResponse(String username, String details, List<String> roles, String name) {
        this.username = username;
        this.details = details;
        this.roles = roles;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
