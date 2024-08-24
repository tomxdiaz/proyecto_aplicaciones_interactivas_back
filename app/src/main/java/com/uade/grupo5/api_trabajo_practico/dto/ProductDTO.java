package com.uade.grupo5.api_trabajo_practico.dto;

public class ProductDTO {
    private String name;
    private String description;
    private String price;

    public ProductDTO(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
