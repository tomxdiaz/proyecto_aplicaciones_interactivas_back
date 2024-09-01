package com.uade.grupo5.api_trabajo_practico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // public List<Product> getAllProducts() {
    // return products;
    // }

    // public Product getProductById(String id) throws Exception {

    // Product product = products.stream()
    // .filter(p -> p.getId().equals(id))
    // .findFirst().orElseThrow(() -> new RuntimeException("Product not exists"));

    // return product;

    // }

    // public Product createProduct(
    // ProductDTO productDTO) {

    // Product newProduct = new Product();

    // products.add(newProduct);

    // return newProduct;
    // }

}
