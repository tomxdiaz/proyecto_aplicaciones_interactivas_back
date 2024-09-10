package com.uade.grupo5.api_trabajo_practico.dto;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyDTO{
  private long id;
  private long cartId;
  private String buyDate;

  public Buy toEntity(BuyDTO buy){
    return Buy.builder()
              .cartId(buy.getCartId())
              .buyDate(buy.getBuyDate())
              .build();

  }
}