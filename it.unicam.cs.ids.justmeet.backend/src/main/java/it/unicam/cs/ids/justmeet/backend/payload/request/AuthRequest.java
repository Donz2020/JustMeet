package it.unicam.cs.ids.justmeet.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class AuthRequest extends UserRequest{

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
