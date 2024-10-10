package com.nrv.auth_service.config;

import com.nrv.auth_service.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Custom user details class which implements UserDetails.
 * It is used to fetch the user credential from database to be compared
 * with the credentials provided by client. Authentication Provider does
 * the comparison.
 *
 * @author Nirav Parekh
 * @see AuthConfig
 * @see CustomUserDetailsService
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @since 1.0
 */
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    public String getRole() {
        return user.getRole().name();
    }
}
