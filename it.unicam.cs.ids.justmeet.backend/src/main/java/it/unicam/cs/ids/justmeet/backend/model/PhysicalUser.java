package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class PhysicalUser extends User implements IPhysicalUser {

    @Id
    @NotBlank
    private String email;

    @NotBlank
    private String cognome;

    //@DBRef
    private Set<UserRole> roles = new HashSet<>();

    @Override
    public void setUniqueID(String email) {
        this.email = email;
    }

    @Override
    public String getUniqueID() {
        return email;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String getDetails() {
        return String.format("%s %s", super.name, cognome);
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
