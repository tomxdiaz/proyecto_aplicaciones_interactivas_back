package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

@Service
public class ProductService {

        @Autowired
        private ProductRepository productRepository;

        public List<Product> getAllProducts() {
                List<Product> products = productRepository.getAllProducts();
                return products;
        }

        public Product getProductById(String id) throws Exception {
                Product product = productRepository.getProductById(id);
                return product;
        }

        public Product createProduct(
                        ProductDTO productDTO) {
                Product product = productRepository.createProduct(productDTO);
                return product;

        }

}
