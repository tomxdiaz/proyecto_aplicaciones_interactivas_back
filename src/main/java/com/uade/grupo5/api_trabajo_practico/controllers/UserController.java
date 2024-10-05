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

  // SIRVE
  @GetMapping("")
  public ResponseEntity<?> getUserData(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User user = userService.getUserByUsername(userDetails.getUsername());
      UserDTO userDTO = user.toDTO();
      return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // SIRVE
  @PutMapping("")
  public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
    try {
      User user = userDTO.toEntity();

      User updatedUser = userService.updateUser(user);

      UserDTO updatedUserDTO = updatedUser.toDTO();
      return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO
  // @DeleteMapping("/{id}")
  // public ResponseEntity<?> deleteUser(@PathVariable Long id) {
  // try {
  // userService.deleteUser(id);

  // return ResponseEntity.status(HttpStatus.OK).body(null);
  // } catch (Exception error) {
  // return
  // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
  // }
  // }

  // TODO
  // @GetMapping("")
  // public ResponseEntity<?> getAllUsers() {
  // try {
  // List<User> users = userService.getAllUsers();
  // List<UserDTO> usersDTO = users.stream().map(user ->
  // user.toDTO()).collect(Collectors.toList());
  // return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
  // } catch (Exception error) {
  // return
  // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
  // }
  // }

}
