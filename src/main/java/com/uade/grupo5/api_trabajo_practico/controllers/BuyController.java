package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.BuyService;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/buy")
public class BuyController {
  @Autowired
  private BuyService buyService;

  @Autowired
  private UserService userService;

  // ** TOKEN FUNCIONANDO **
  @GetMapping("")
  public ResponseEntity<?> getUserBuys(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      List<BuyDTO> buys = buyService.getUserBuys(authUser.getId()).stream().map(Buy::toDTO).toList();

      return ResponseEntity.status(HttpStatus.OK).body(buys);

    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

}
