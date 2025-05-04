package de.pingaroo.backend.service.impl;

import de.pingaroo.backend.domain.entities.User;
import de.pingaroo.backend.repositories.UserRepository;
import de.pingaroo.backend.service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${jwt.secret}")
  private String secretKey;

  private final long jwtExpirationInMillis = 86400000L; // 24 hours in milliseconds

  @Override
  public UserDetails register(String email, String password, String firstName, String lastName) {
    if (userRepository.findByEmailIgnoreCase(email).isPresent()) {
      throw new IllegalArgumentException("User already exists with email: " + email);
    }

    User user = User.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .firstName(firstName)
        .lastName(lastName)
        .isActive(true)
        .build();

    userRepository.save(user);
    return userDetailsService.loadUserByUsername(email);
  }

  @Override
  public UserDetails authenticate(String email, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    return userDetailsService.loadUserByUsername(email);
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public UserDetails validateToken(String token) {
    return extractUsername(token) != null
        ? userDetailsService.loadUserByUsername(extractUsername(token))
        : null;
  }

  private String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }
}
