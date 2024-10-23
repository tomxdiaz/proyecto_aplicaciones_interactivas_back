package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.dto.WishListItemDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  @JsonBackReference
  private User user;

  @ManyToOne
  @JoinColumn(nullable = false, name = "product_id")
  private Product product;

  public WishListItemDTO toDTO() {
    return WishListItemDTO.builder()
        .id(this.id)
        .user(user)
        .product(this.product)
        .build();

  }
}
