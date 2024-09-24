package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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

  @NotNull
  @Column(nullable = false)
  private LocalDate buyDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, name = "cart_id")
  private Cart cart;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
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