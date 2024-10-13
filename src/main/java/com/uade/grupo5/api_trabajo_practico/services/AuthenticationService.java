package com.uade.grupo5.api_trabajo_practico.services;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationResponse;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.RegisterRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import jakarta.security.auth.message.AuthException;

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

  // ** SIRVE **
  public AuthenticationResponse register(RegisterRequest request) throws Exception {
    try{
      User user = userService.createUser(request);

      String jwtToken = jwtService.generateToken(user);

      return AuthenticationResponse.builder()
          .accessToken(jwtToken)
          .build();
    }catch(UserException error){
      throw new UserException(error.getMessage());
    }catch (Exception error){
      throw new Exception("[AuthenticationService.register] -> " + error.getMessage());
    }
  }

  // ** SIRVE **
  public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
    try{
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));

      User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new UserException("El usuario " + request.getUsername() + " no existe."));

      String jwtToken = jwtService.generateToken(user);

      return AuthenticationResponse.builder()
          .accessToken(jwtToken)
          .build();
    }catch (AuthenticationException error){
      System.out.printf("[AuthenticationService.authenticate] -> %s", error.getMessage());
      throw new AuthException("Usuario o contraseña incorrecto.");
    }catch (UserException error) {
      throw new UserException(error.getMessage());
    }catch (Exception error) {
      throw new Exception("[AuthenticationService.authenticate] -> " + error.getMessage());
    }
  }
}
