package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.services.BuyService;

@RestController
@RequestMapping("/buy")
public class BuyController {
  @Autowired
  private BuyService buyService;

  // TODO -> INFO DEL TOKEN
  @GetMapping("/{id}")
  public ResponseEntity<?> getUserBuys(@PathVariable Long id) {
    try {
      List<BuyDTO> buys = buyService.getUserBuys(id).stream().map(Buy::toDTO).toList();

      return ResponseEntity.status(HttpStatus.OK).body(buys);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO
  // @GetMapping("")
  // public ResponseEntity<?> getBuys() {
  // try {
  // List<BuyDTO> allBuysDTO = buyService.getAllBuys().stream().map(buy ->
  // buy.toDTO()).toList();

  // return ResponseEntity.status(HttpStatus.OK).body(allBuysDTO);
  // } catch (Exception error) {
  // return
  // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
  // }

  // }

  // TODO
  // @DeleteMapping("/{id}")
  // public ResponseEntity<?> deleteBuy(@PathVariable Long id) {
  // try {
  // buyService.deleteBuy(id);
  // return ResponseEntity.status(HttpStatus.OK).body("Compra borrada
  // correctamente!");
  // } catch (Exception error) {
  // return
  // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
  // }
  // }

}
