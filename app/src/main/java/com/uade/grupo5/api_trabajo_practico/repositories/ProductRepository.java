package com.uade.grupo5.api_trabajo_practico.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    private int nextID = 1;

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductByTitle(String title) throws Exception {

        Product product = products.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst().orElseThrow(() -> new RuntimeException("Product already exists"));

        return product;

    }

    public Product createProduct(
            ProductDTO productDTO) {

        if (products.stream().anyMatch(p -> p.getTitle().equals(productDTO.getTitle()))) {
            throw new RuntimeException("Product already exists");
        }

        Product newProduct = new Product(
                String.valueOf(nextID++),
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
