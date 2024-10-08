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
        public ResponseEntity<String> initializeDB(@AuthenticationPrincipal UserDetails userDetails) {

                try {
                        User authUser = userService.getUserByUsername(userDetails.getUsername());

                        System.out.println(authUser);

                        return ResponseEntity.status(HttpStatus.OK).body("Base inicializada correctamente!");

                } catch (Exception error) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
                }
        }

        @PutMapping("/makeAllUsersAdmin")
        public ResponseEntity<String> makeAllUsersAdmin() {
                try {
                        for (User user : userService.getAllUsers()) {
                                userService.updateRole(user, Role.ADMIN);
                        }
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body("Todos los usuarios son ahora administradores!");

                } catch (Exception error) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
                }
        }

}
