package com.uade.grupo5.api_trabajo_practico.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public Product createProduct(String name, String description, String price) {
        Product newProduct = new Product(name, description, price);

        products.add(newProduct);

        return newProduct;
    }

}
