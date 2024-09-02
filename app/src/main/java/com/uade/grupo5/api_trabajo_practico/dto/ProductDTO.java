package com.uade.grupo5.api_trabajo_practico.dto;

import java.util.List;

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
    private String category;
    private boolean featured;

}
