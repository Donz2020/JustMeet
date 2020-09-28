package it.unicam.cs.ids.justmeet.backend.payload.request;

import java.util.Set;

public class RoleEditRequest extends UserRequest{

    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
