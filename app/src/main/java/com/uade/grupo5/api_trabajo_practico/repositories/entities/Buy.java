package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Buy {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long cartId;
  private String buyDate;


  public BuyDTO toDTO(Buy buy){
    return BuyDTO.builder()
            .cartId(buy.getCartId())
            .buyDate(buy.getBuyDate())
            .build();
  }

}