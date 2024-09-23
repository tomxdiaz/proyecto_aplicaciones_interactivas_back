package com.uade.grupo5.api_trabajo_practico.dto;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotEmpty
    private List<String> images;
    @NotNull
    private String additionalInfo;
    @NotNull
    private int stock;
    @NotNull
    private boolean featured;
    private CategoryDTO category;

    public Product toEntity() {
        return new Product(
                this.id,
                this.title,
                this.description,
                this.price,
                this.images,
                this.additionalInfo,
                this.stock,
                this.featured,
                this.category.toEntity());
    }

}
