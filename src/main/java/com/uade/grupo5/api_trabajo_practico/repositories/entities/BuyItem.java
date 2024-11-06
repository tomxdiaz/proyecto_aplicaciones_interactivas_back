package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class BuyItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private String title;
  @NotNull
  @Column(nullable = false)
  private String description;
  @NotNull
  @Column(nullable = false)
  private double price;
  @NotNull
  @Column(nullable = false)
  private int quantity;
  @NotEmpty
  @ElementCollection
  @Column(nullable = false, columnDefinition = "LONGTEXT")
  private List<String> images;
  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "buy_id")
  @JsonBackReference
  private Buy buy;

  public double getSubTotal() {
    return this.price * this.quantity;
  }

}
