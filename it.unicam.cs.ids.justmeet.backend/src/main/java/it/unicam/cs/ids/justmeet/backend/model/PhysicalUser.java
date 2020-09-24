package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class PhysicalUser extends User implements IPhysicalUser {

    @Id
    @NotBlank
    @Size(min = 8, max = 64)
    private String email;

    @NotBlank
    private String surname;

    //@DBRef
    private Set<UserRole> roles = new HashSet<>();

    public PhysicalUser() {
        super.setActive(true);
    }

    @Override
    public void setUniqueID(String email) {
        if(Utils.isValidEmailAddress(email))
            this.email = email;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String getUniqueID() {
        return email;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getDetails() {
        return String.format("%s %s", super.name, surname);
    }

    @Override
    public Set<UserRole> getRole() {
        return roles;
    }

    @Override
    public void setRole(Set<UserRole> roles) {
        this.roles = roles;
    }
}
