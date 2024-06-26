package org.petspa.petcaresystem.config;

import lombok.AllArgsConstructor;
import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {
    private AuthenUser authenUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(authenUser.getRole().getRoleName()));
    }

    @Override
    public String getPassword() {
        return authenUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authenUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authenUser.getStatus() == authenUser.getStatus();
    }
}
