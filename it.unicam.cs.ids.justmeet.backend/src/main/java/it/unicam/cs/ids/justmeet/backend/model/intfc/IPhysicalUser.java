package it.unicam.cs.ids.justmeet.backend.model.intfc;


import it.unicam.cs.ids.justmeet.backend.model.UserRole;

import java.util.Set;

public interface IPhysicalUser extends IUser{

    void setRole(Set<UserRole> roles);

    String getCognome();

    void setCognome(String cognome);
}
