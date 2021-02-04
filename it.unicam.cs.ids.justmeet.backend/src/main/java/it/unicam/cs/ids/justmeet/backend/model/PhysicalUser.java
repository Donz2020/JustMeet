package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PhysicalUser extends User implements IPhysicalUser {

    @NotBlank
    @Size(min = 8, max = 64)
    private String email;

    @NotBlank
    private String surname;

    //@DBRef
    private Set<UserRole> roles = new HashSet<>();

    @NotNull
    private LocalDate birthDate;

    public PhysicalUser() {
        super.setActive(true);
        setRole(UserRole.buildRoles(EnumUserRole.STD));
    }

    @Override
    public void setUsername(String username) {
        if(Utils.isValidEmailAddress(username))
            email = username;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String getUsername() {
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
        if(UserRole.isRolePresent(roles, EnumUserRole.VRF)) throw new IllegalArgumentException();
        this.roles = roles;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
