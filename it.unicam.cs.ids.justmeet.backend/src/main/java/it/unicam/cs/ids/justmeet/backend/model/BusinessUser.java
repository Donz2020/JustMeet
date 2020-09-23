package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class BusinessUser extends User implements IUser {

    @Id
    @NotBlank
    @Size(min = 8, max = 12)
    private String VATNumber;

    public String getDetails() {
        return String.format("%s", super.name);
    }

    public String getUniqueID() {
        return VATNumber;
    }

    public void setUniqueID(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    public Set<UserRole> getRole() {
        Set<UserRole> role = new HashSet<UserRole>();
        role.add(new UserRole(EnumUserRole.VRF));
        return role;
    }
}
