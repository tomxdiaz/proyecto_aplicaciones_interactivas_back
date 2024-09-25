package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @NotNull
    @Column(nullable = false)
    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "cart_id")
    @JsonBackReference
    private Cart cart;

    public ItemDTO toDTO() {
        return ItemDTO.builder()
                .id(this.id)
                .product(this.product)
                .quantity(this.quantity)
                .cart(this.cart)
                .build();
    }

    public double getSubTotal() {
        return this.product.getPrice() * this.quantity;
    }

}