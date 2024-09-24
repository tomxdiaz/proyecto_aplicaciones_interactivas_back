package com.uade.grupo5.api_trabajo_practico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationResponse;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.RegisterRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final JwtService jwtService;
  @Autowired
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) throws Exception {
    User user = new User(null, request.getUserName(), request.getName(), request.getSurname(),
        request.getEmailAddress(), request.getBirthDate(), passwordEncoder.encode(request.getPassword()),
        request.getRol(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUserName(),
            request.getPassword()));
    User user = userRepository.findByUserName(request.getUserName())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }
}
