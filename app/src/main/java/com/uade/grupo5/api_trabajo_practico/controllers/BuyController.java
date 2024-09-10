package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  public ResponseEntity<List<BuyDTO>> getBuys() throws Exception{
    List<BuyDTO> buys = buyService.getBuys().stream()
        .map(buy -> BuyDTO.builder()
                    .id(buy.getId())
                    .cartId(buy.getCartId())
                    .buyDate(buy.getBuyDate())
                    .build())
        .toList();
    
    return ResponseEntity.status(200).body(buys);
  }
}
