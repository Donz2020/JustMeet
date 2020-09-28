package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "roles")
public class UserRole {

    @Id
    private String id;

    private EnumUserRole name;

    public UserRole(EnumUserRole name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnumUserRole getName() {
        return name;
    }

    public void setName(EnumUserRole name) {
        this.name = name;
    }

    public static Set<UserRole> fromString(Set<String> id) {
        Set<UserRole> roles = new HashSet<>();
        for (String s: id)
            roles.add(new UserRole(EnumUserRole.valueOf(s)));
        return roles;
    }
}
