package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        List<ProductDTO> productsDTO = products.stream()
                .map(product -> new ProductDTO(product.getName(), product.getDescription(), product.getPrice()))
                .collect(Collectors.toList());
        return productsDTO;
    }

    public ProductDTO createProduct(String name, String description, String price) {
        Product product = productRepository.createProduct(name, description, price);
        ProductDTO productDTO = new ProductDTO(product.getName(), product.getDescription(), product.getPrice());
        return productDTO;

    }

}
