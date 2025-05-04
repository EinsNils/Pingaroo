package de.pingaroo.backend.controller;

import de.pingaroo.backend.domain.dtos.AuthResponse;
import de.pingaroo.backend.domain.dtos.LoginRequest;
import de.pingaroo.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping()
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    UserDetails userDetails =
        authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

    String token = authenticationService.generateToken(userDetails);

    AuthResponse authResponse =
        AuthResponse.builder().token(token).expiresIn(86400).build(); // 24 hours in seconds
    return ResponseEntity.ok(authResponse);
  }
}
