package com.uade.grupo5.api_trabajo_practico.dto;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyDTO{
  private long id;
  /* private Cart cart; */
  private String buyDate;
  private UserDTO user;

  public Buy toEntity(){
    return Buy.builder()
              /* .cart(this.cartId.toDTO()) */
              .buyDate(this.buyDate)
              .user(this.user.toEntity())
              .build();

  }
}