package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.dto.SearchDTO;

import jakarta.persistence.*;
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
public class Search {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(nullable = false)
  private LocalDateTime date;


  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  @JsonBackReference
  private User user;


  @ManyToOne
  @JoinColumn(nullable = false, name = "product_id")
  private Product product;



  public SearchDTO toDTO() {
    return SearchDTO.builder()
        .id(this.id)
        .date(this.date)
        .product(this.product)
        .user(user)
        .build();
  }
}
