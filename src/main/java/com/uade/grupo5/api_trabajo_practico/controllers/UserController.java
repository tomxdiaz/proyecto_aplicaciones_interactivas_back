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
import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.ResponseData;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  // ** SIRVE **
  @GetMapping("")
  public ResponseEntity<ResponseData<?>> getUserData(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      UserDTO userDTO = authUser.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(userDTO));

    } catch (UserException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));
    } catch (Exception error) {
      System.out.printf("[UserController.getUserData] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo encontrar el usuario"));
    }
  }

  // ** SIRVE **
  @PutMapping("")
  public ResponseEntity<ResponseData<?>> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDTO userDTO) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      User user = userDTO.toEntity();

      authUser.updateData(user);

      User updatedUser = userService.updateUser(authUser);

      UserDTO updatedUserDTO = updatedUser.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(updatedUserDTO));

    } catch (UserException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));
      
    } catch (Exception error) {
      System.out.printf("[UserController.updateUser] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo actualizar el usuario"));
    }
  }

}
