package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.util.List;

public class Product {
    private String id;
    private String title;
    private String description;
    private double price;
    private List<String> images;
    private String additionalInfo;
    private int stock;
    private String category;
    private boolean featured;

    public Product(String id, String title, String description, double price, List<String> images,
            String additionalInfo, int stock, String category, boolean featured) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.images = images;
        this.additionalInfo = additionalInfo;
        this.stock = stock;
        this.category = category;
        this.featured = featured;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getImages() {
        return images;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public boolean getFeatured() {
        return featured;
    }

}
