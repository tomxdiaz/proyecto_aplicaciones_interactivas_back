package com.uade.grupo5.api_trabajo_practico.dto;

import java.time.LocalDate;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyDTO {
  private long id;
  /* private Cart cart; */
  private LocalDate buyDate;
  private User user;

  public Buy toEntity() {
    return Buy.builder()
        /* .cart(this.cartId.toDTO()) */
        .buyDate(this.buyDate)
        .user(this.user)
        .build();

  }
}