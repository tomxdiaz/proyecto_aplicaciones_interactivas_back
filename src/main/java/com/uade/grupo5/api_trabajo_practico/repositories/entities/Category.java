package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import com.uade.grupo5.api_trabajo_practico.dto.CategoryDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public CategoryDTO toDTO() {
        return new CategoryDTO(
                this.id,
                this.name,
                this.description);
    }

}
