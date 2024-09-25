package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Buy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private LocalDate buyDate;

  @NotEmpty
  @OneToMany(mappedBy = "buy", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<BuyItem> items;

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

  public BuyItem toBuyItem(Item item) {
    return BuyItem.builder()
                  .title(item.getProduct().getTitle())
                  .description(item.getProduct().getDescription())
                  .price(item.getProduct().getPrice())
                  .images(item.getProduct().getImages())
                  .buy(this)
                  .build();
  }

}