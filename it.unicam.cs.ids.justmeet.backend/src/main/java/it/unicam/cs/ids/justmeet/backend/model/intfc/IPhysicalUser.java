package it.unicam.cs.ids.justmeet.backend.model.intfc;


import it.unicam.cs.ids.justmeet.backend.model.UserRole;

import java.time.LocalDate;
import java.util.Set;

public interface IPhysicalUser extends IUser{

    void setRole(Set<UserRole> roles);

    String getSurname();

    void setSurname(String surname);

    LocalDate getBirthDate();

    void setBirthDate(LocalDate birthDate);
}
