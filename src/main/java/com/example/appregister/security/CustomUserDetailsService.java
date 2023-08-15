package com.example.appregister.security;

import com.example.appregister.entity.Client;
import com.example.appregister.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private ClientService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client user = userService.findClientByEmail(email);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities("USER").build();
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
