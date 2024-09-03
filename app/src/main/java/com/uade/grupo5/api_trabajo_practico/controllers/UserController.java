package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
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

}
