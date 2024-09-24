package com.uade.grupo5.api_trabajo_practico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationResponse;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.RegisterRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final JwtService jwtService;
  @Autowired
  private final AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;

  public AuthenticationResponse register(RegisterRequest request) throws Exception {

    User user = userService.createUser(request);

    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));
    User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }
}
