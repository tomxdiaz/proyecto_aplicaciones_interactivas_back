package com.uade.grupo5.api_trabajo_practico.dto;

import java.sql.Date;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Search;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchDTO {
  private Long id;
  @NotNull
  private Date date;
  @NotNull
  private Product product;
  @NotNull
  private User user;

  public Search toEntity() {
    return Search.builder()
        .id(this.id)
        .date(this.date)
        .product(this.product)
        .user(this.user)
        .build();
  }
}
