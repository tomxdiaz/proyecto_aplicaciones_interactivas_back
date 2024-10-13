package com.uade.grupo5.api_trabajo_practico.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.AuthenticationResponse;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.RegisterRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.ResponseData;
import com.uade.grupo5.api_trabajo_practico.services.AuthenticationService;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  private final AuthenticationService authService;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<?>> register(
      @RequestBody RegisterRequest request) {
    try {
      AuthenticationResponse authResponse = authService.register(request);
      if(authResponse.getAccessToken() != null) return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(authResponse));

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error("No se pudo registrar el usuario"));

    } catch (UserException error) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[AuthenticationController.register] -> %s", error.getMessage() );

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo registrar el usuario"));
    }

  }

  @PostMapping("/authenticate")
  public ResponseEntity<ResponseData<?>> authenticate(
      @RequestBody AuthenticationRequest request) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(authService.authenticate(request)));

    }catch (UserException error) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseData.error(error.getMessage()));
    }catch(AuthException error){
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseData.error(error.getMessage()));
    }catch (Exception error) {
      System.out.printf("[AuthenticationController.authenticate] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));
    }

  }
}