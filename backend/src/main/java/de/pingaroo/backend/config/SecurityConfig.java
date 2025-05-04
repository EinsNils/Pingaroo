package de.pingaroo.backend.config;

import de.pingaroo.backend.repositories.UserRepository;
import de.pingaroo.backend.security.CustomUserDetailsService;
import de.pingaroo.backend.security.JwtAuthenticationFilter;
import de.pingaroo.backend.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter(
      AuthenticationService authenticationService) {
    return new JwtAuthenticationFilter(authenticationService);
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new CustomUserDetailsService(userRepository);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/endpoints/**")
                    .permitAll())
        .csrf(AbstractHttpConfigurer::disable) // TODO: Enable this later
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
      
      return config.getAuthenticationManager();
  }
}
