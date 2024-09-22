package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;
import com.uade.grupo5.api_trabajo_practico.services.BuyService;

@RestController
@RequestMapping("/buy")
public class BuyController {
  @Autowired
  private BuyService buyService;

  @GetMapping("")
  public ResponseEntity<List<BuyDTO>> getBuys() throws Exception {
    List<BuyDTO> buys = buyService.getBuys().stream().map(buy -> buy.toDTO()).toList();

    return ResponseEntity.status(HttpStatus.OK).body(buys);
  }

  /*
   * // @TODO: Este metodo aun no funciona
   * 
   * @GetMapping("/{id}")
   * public ResponseEntity<List<BuyDTO>> getUserBuys(@PathVariable Long id) throws
   * Exception{
   * List<BuyDTO> buys =
   * buyService.getUserBuys(id).stream().map(Buy::toDTO).toList();
   * 
   * return ResponseEntity.status(HttpStatus.OK).body(buys);
   * }
   */
}
