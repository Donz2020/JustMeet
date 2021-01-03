package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.configuration.BCrypt;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public abstract class User implements IUser {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @NotBlank
    protected String name;

    @NotBlank
    @Size(min=5, max = 60)
    private String password;

    private boolean active;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public void setUsername(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = BCrypt.passwordEncoder().encode(password);
    }

    @Override
    public String getDetails() {
        return "";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    private String generateString(User user) {
        return String.format("%s-%s", user.getUsername(), user.getPassword());//, user.getDetails());
    }

    @Override
    public boolean equals(Object object) {
        return generateString(this).equals(generateString((User)object));
    }

    @Override
    public int hashCode() {
        return generateString(this).hashCode();
    }

    @Override
    public String toString() { return generateString(this);}
}
