package org.scaler.ecomuser.security.services;

import org.scaler.ecomuser.models.EcomUser;
import org.scaler.ecomuser.repositories.UserRepository;
import org.scaler.ecomuser.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EcomUser> user = userRepository.findByEmail(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User with email : " + username + " doesn't exist.");

        return new CustomUserDetails(user.get());
    }
}
