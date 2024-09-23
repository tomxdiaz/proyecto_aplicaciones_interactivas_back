package com.uade.grupo5.api_trabajo_practico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.UserDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try{
          User user = userService.getUserById(id);
          UserDTO userDTO = user.toDTO();
          return ResponseEntity.status( HttpStatus.OK).body(userDTO);
        }catch(Exception error){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
      try{
        User user = userDTO.toEntity();

        User updatedUser = userService.updateUser(user);

        UserDTO updatedUserDTO = updatedUser.toDTO();
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
      try{
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
}

}
