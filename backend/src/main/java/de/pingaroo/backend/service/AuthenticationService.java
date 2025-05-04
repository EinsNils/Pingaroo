package de.pingaroo.backend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    
    UserDetails authenticate(String email, String password);
    
    String generateToken(UserDetails userDetails);
    
    UserDetails validateToken(String token);
    
    UserDetails register(String email, String password, String firstName, String lastName);
}
