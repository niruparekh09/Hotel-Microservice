package com.nrv.auth_service.config;

import com.nrv.auth_service.exception.ResourceNotFoundException;
import com.nrv.auth_service.model.User;
import com.nrv.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom user details Implementation service class which
 * retrieve user-specific details based on the provided username.
 * Authentication Provider delegates to this class for this purpose.
 *
 * @author Nirav Parekh
 * @see AuthConfig
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @since 1.0
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found"));
        return new CustomUserDetails(user);
    }
}
