package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String title;
    @NotNull
    @Column(nullable = false)
    private String description;
    @NotNull
    @Column(nullable = false)
    private double price;
    @NotEmpty
    @ElementCollection
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private List<String> images;
    @NotNull
    @Column(nullable = false)
    private String additionalInfo;
    @NotNull
    @Column(nullable = false)
    private int stock;
    @NotNull
    @Column(nullable = false)
    private boolean featured;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public ProductDTO toDTO() {
        return new ProductDTO(
                this.id,
                this.title,
                this.description,
                this.price,
                this.images,
                this.additionalInfo,
                this.stock,
                this.featured,
                this.category);
    }

}
