package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String getString() {
        return name.name();
    }

    public static boolean isRolePresent(Set<UserRole> roles, EnumUserRole role) {
        return roles.contains(role);
    }

    public static Set<UserRole> fromString(Set<String> id) {
        Set<UserRole> roles = new HashSet<>();
        id.stream().forEach(s -> roles.add(new UserRole(EnumUserRole.valueOf(s))));
        return roles;
    }

    public static Set<UserRole> buildRoles(EnumUserRole... roles){
        Set<UserRole> temp = new HashSet<>();
        for (EnumUserRole r: roles)
            temp.add(new UserRole(r));
        return temp;
    }

    @Override
    public String toString() {
        return name.name();
    }
}
