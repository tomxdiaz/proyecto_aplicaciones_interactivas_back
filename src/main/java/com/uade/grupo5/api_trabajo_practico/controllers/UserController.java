package com.uade.grupo5.api_trabajo_practico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.UserDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  // ** SIRVE **
  @GetMapping("")
  public ResponseEntity<?> getUserData(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      UserDTO userDTO = authUser.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(userDTO);

    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // ** SIRVE **
  @PutMapping("")
  public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDTO userDTO) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      User user = userDTO.toEntity();

      user.setId(authUser.getId());

      User updatedUser = userService.updateUser(user);

      UserDTO updatedUserDTO = updatedUser.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);

    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

}
