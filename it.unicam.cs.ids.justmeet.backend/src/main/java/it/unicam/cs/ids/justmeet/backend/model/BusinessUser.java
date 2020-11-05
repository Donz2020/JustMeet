package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class BusinessUser extends User implements IUser {

    @NotBlank
    @Size(min = 8, max = 12)
    private String VATNumber;

    public BusinessUser() {
        setActive(false);
    }

    public String getDetails() {
        return String.format("%s, %s",VATNumber,  super.name);
    }

    @Override
    public String getUsername() {
        return VATNumber;
    }

    @Override
    public void setUsername(String username) {
        if(Utils.isValidVATNumber(username))
            VATNumber = username;
        else
            throw new IllegalArgumentException();
    }

    public Set<UserRole> getRole() {
        return UserRole.buildRoles(EnumUserRole.VRF);
    }
}
