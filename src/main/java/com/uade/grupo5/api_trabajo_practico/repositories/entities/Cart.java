package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.uade.grupo5.api_trabajo_practico.dto.CartDTO;
import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "user" })
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items;

    public double calculateTotalPrice() {
        double total = 0;

        for (Item item : this.getItems()) {
            total += item.getSubTotal();
        }

        return total;
    }

    public List<BuyItem> generateBuyItems() {
        List<BuyItem> buyItems = new ArrayList<>();
        this.getItems().forEach(item -> {
            if (item.getQuantity() > item.getProduct().getStock()) {
                throw new RuntimeException("Not enough stock of product: " + item.getProduct().getTitle());
            }
            BuyItem buyItem = item.toBuyItem();
            buyItems.add(buyItem);
        });

        return buyItems;
    }

    public CartDTO toDTO() {
        // Crear un nuevo objeto CartDTO
        CartDTO cartDTO = new CartDTO();

        // Asignar los valores básicos
        cartDTO.setId(this.getId());
        cartDTO.setUser(this.getUser());

        // Convertir la lista de items de Cart a una lista de ItemDTO
        List<ItemDTO> itemDTOs = this.getItems().stream()
                .map(item -> item.toDTO())
                .collect(Collectors.toList());

        // Asignar la lista de ItemDTO al CartDTO
        cartDTO.setItems(itemDTOs);
        cartDTO.setTotalPrice(this.calculateTotalPrice());

        return cartDTO; // Devolver el CartDTO convertido
    }

}
