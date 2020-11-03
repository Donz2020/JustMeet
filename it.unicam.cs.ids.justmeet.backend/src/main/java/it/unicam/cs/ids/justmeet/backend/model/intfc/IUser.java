package it.unicam.cs.ids.justmeet.backend.model.intfc;

import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
public interface IUser {

    long getID();

    void setID(long id);

    String getUsername();

    void setUsername(String username);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String getDetails();

    boolean isActive();

    void setActive(boolean active);

    Set<UserRole> getRole();
}
