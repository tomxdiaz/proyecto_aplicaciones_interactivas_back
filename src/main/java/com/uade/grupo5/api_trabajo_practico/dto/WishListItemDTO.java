package com.uade.grupo5.api_trabajo_practico.dto;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.WishListItem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishListItemDTO {
  private Long id;
  private UserDTO user;
  private ProductDTO product;

  public WishListItem toEntity(){
    return WishListItem.builder()
                      .id(this.id)
                      .user(this.user.toEntity())
                      .product(this.product.toEntity())
                      .build();
  }
}
