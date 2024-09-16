package com.uade.grupo5.api_trabajo_practico.dto;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private List<String> images;
    private String additionalInfo;
    private int stock;
    /* private CategoryDTO category; */
    private boolean featured;

    public Product toEntity() {
        return new Product(
                this.id,
                this.title,
                this.description,
                this.price,
                this.images,
                this.additionalInfo,
                this.stock,
                /* this.category, */
                this.featured);
    }

}
