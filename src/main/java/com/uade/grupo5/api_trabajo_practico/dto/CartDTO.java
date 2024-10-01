package com.uade.grupo5.api_trabajo_practico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long id;
    @NotNull
    @JsonBackReference
    private User user;
    @NotNull
    private List<ItemDTO> items;
    @NotNull
    private double totalPrice;
}
