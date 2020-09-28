package it.unicam.cs.ids.justmeet.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
