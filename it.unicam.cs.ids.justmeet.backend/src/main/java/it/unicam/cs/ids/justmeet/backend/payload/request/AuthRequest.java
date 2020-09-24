package it.unicam.cs.ids.justmeet.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
