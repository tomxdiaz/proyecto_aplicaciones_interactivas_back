package com.uade.grupo5.api_trabajo_practico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    @NotNull
    private Product product;
    @NotNull
    private int quantity;
    @JsonIgnore
    @NotNull
    private Cart cart;

    public Long getProductId() {
        return this.product.getId();
    }

    public Long getCartId() {
        return this.cart.getId();
    }

    public Item toEntity() {
        return Item.builder()
                .id(this.id)
                .product(this.product)
                .quantity(this.quantity)
                .cart(this.cart)
                .build();
    }
}
