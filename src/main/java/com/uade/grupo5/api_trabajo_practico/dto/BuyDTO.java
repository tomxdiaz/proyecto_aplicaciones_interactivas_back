package com.uade.grupo5.api_trabajo_practico.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.BuyItem;
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
  private Long id;
  @NotNull
  private List<BuyItem> items;
  @NotNull
  private LocalDateTime buyDate;
  @NotNull
  @JsonBackReference
  private User user;
  @NotNull
  private double totalPrice;

  public Buy toEntity() {
    return Buy.builder()
        .buyDate(this.buyDate)
        .user(this.user)
        .items(this.items)
        .build();
  }
}