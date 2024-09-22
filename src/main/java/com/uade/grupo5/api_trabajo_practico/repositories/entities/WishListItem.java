package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.uade.grupo5.api_trabajo_practico.dto.WishListItemDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
  
  public WishListItemDTO toDTO(){
    return WishListItemDTO.builder()
                          .id(this.id)
                          .user(this.user.toDTO())
                          .product(this.product.toDTO())
                          .build();
  }
}
