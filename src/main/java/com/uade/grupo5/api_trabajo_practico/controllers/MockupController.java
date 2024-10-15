package com.uade.grupo5.api_trabajo_practico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.ResponseData;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Role;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/mockup")
public class MockupController {
  @Autowired
  private UserService userService;

  // TODO: AGREGAR DATOS DE PRUEBA -> CATEGORIAS
  @PostMapping("/initialize")
  public ResponseEntity<ResponseData<String>> initializeDB(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      userService.getUserByUsername(userDetails.getUsername());

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success("Base inicializada correctamente!"));

    } catch (UserException error) {
      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.error(error.getMessage()));
    } catch (Exception error) {
      System.out.printf("[MockupController.initializeDB] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo inicializar la DB"));
    }
  }

  @PutMapping("/makeAllUsersAdmin")
  public ResponseEntity<ResponseData<String>> makeAllUsersAdmin() {
    try {
      for (User user : userService.getAllUsers()) {
        userService.updateRole(user, Role.ADMIN);
      }
      return ResponseEntity.status(HttpStatus.OK)
                  .body(ResponseData.success("Todos los usuarios son ahora administradores!"));

    } catch (Exception error) {
      System.out.printf("[MockupController.makeAllUsersAdmin] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error(error.getMessage()));
    }
  }

}
