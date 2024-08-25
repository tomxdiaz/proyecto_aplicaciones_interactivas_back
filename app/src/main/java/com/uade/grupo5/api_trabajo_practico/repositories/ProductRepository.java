package com.uade.grupo5.api_trabajo_practico.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public Product createProduct(
            ProductDTO productDTO) {

        Product newProduct = new Product(
                productDTO.getId(),
                productDTO.getTitle(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getImages(),
                productDTO.getAdditionalInfo(),
                productDTO.getStock(),
                productDTO.getCategory(),
                productDTO.getFeatured());

        products.add(newProduct);

        return newProduct;
    }

}
