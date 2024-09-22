package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;

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
public class Buy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate buyDate;

  /*
   * @OneToOne(cascade = CascadeType.ALL)
   * 
   * @JoinColumn(name = "cart_id")
   * private Cart cart;
   */

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

  public BuyDTO toDTO() {
    return BuyDTO.builder()
        .id(this.id)
        .buyDate(this.buyDate)
        .user(user)
        .build();
  }

}