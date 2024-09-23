package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.dto.SearchDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Search {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private Date date;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "product_id")
  private Product product;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  @JsonBackReference
  private User user;

  public SearchDTO toDTO() {
    return SearchDTO.builder()
        .id(this.id)
        .date(this.date)
        .product(this.product)
        .user(user)
        .build();
  }
}
