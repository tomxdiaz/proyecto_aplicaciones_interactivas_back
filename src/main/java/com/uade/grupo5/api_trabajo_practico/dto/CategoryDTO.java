package com.uade.grupo5.api_trabajo_practico.dto;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;

    public Category toEntity() {
        return new Category(
                this.id,
                this.name,
                this.description);
    }

}
