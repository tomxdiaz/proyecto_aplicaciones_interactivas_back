package com.uade.grupo5.api_trabajo_practico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws Exception{
        User user = userService.getUserById(id);
        UserDTO userDTO = user.toDTO();
        return ResponseEntity.status( HttpStatus.OK).body(userDTO);
    }
//ALTA: create a user
    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(
                    @RequestBody UserDTO userDTO) throws Exception {

            User user = userDTO.toEntity();

            User createdUser = userService.createUser(user);

            UserDTO createdUserDTO = createdUser.toDTO();
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }
//MODIFY: update
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
                    @RequestBody UserDTO userDTO) throws Exception {

            User user = userDTO.toEntity();

            User updatedUser = userService.updateUser(user);

            UserDTO updatedUserDTO = updatedUser.toDTO();
            return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
    }
//BAJA: delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
                    @PathVariable Long id) throws Exception {

            userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.OK).body(null);
}

}
