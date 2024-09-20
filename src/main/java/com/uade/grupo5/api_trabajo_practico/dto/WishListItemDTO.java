package com.uade.grupo5.api_trabajo_practico.dto;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.WishListItem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishListItemDTO {
  private Long id;
  private User user;
  private Product product;

  public WishListItem toEntity() {
    return WishListItem.builder()
        .id(this.id)
        .user(this.user)
        .product(this.product)
        .build();
  }
}
