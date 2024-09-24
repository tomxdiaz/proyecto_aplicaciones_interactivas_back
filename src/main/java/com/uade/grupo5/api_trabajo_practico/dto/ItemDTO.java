package com.uade.grupo5.api_trabajo_practico.dto;


import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private Product product;
    private int quantity;

}
