package it.unicam.cs.ids.justmeet.backend.configuration.service;


import java.util.Collection;
import java.util.stream.Collectors;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    IUser user;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(IUser user, Collection<? extends GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(IUser user) {
        return new UserDetailsImpl(user, user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    @Override
    public boolean equals(Object o) { return user.equals(o); }

    @Override
    public int hashCode() {
        return user.hashCode();
    }


}
