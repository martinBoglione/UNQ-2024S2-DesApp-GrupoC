package ar.edu.unq.desapp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ar.edu.unq.desapp.model.DTOs.LoginUserDto;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private CustomUserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    public UserDetails authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        return userDetailsService.loadUserByUsername(input.email());
    }
}
