package com.uade.grupo5.api_trabajo_practico.dto;

import java.time.LocalDate;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import jakarta.validation.constraints.NotNull;
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
  @NotNull
  private Cart cart;
  @NotNull
  private LocalDate buyDate;
  @NotNull
  private User user;

  public Buy toEntity() {
    return Buy.builder()
        .cart(this.cart)
        .buyDate(this.buyDate)
        .user(this.user)
        .build();

  }
}