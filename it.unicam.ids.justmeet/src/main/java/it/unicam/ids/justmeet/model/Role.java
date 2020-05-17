package it.unicam.ids.justmeet.model;

import javax.persistence.*;
import java.util.Collection;


    @Entity
    public class Role {

        @Id
       // @GeneratedValue(strategy = GenerationType.AUTO)
        @JoinTable(name= "user", joinColumns = "unique_id")
        private String UniqueID;

        private UserRole privilege;

      /*  @ManyToMany(mappedBy = "roles")
        private Collection<IPhysicalUser> users;*/

      /*  @ManyToMany
        @JoinTable(
                name = "roles_privileges",
                joinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "privilege_id", referencedColumnName = "id"))
        private Collection<Privilege> privileges;*/
    }
