package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
  
  private String buyDate;
  
  /* @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cart_id")
  private Cart cart; */
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


  public BuyDTO toDTO(){
    return BuyDTO.builder()
            /* .cart(this.cart.toDTO()) */
            .buyDate(this.buyDate)
            .user(this.user.toDTO())
            .build();
  }

}