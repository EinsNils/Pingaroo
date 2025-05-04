package de.pingaroo.backend.security;

import de.pingaroo.backend.domain.entities.User;
import de.pingaroo.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user =
        userRepository
            .findByEmailIgnoreCase(email)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with email: " + email));
    
    return new CustomUserDetails(user);
  }
}
