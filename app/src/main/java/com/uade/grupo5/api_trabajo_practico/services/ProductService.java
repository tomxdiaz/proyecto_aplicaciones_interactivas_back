package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

@Service
public class ProductService {

        @Autowired
        private ProductRepository productRepository;

        public List<Product> getAllProducts() {
                List<Product> products = productRepository.findAll();
                return products;
        }

        public Product getProductById(Long id) throws Exception {
                Product product = productRepository.getReferenceById(id);
                return product;

        }

        public Product createProduct(
                        Product product) {
                Product createdProduct = productRepository.save(product);
                return createdProduct;
        }

        public Product updateProduct(
                        Product product) {
                Product updatedProduct = productRepository.save(product);
                return updatedProduct;
        }

        public void deleteProduct(Long id) {
                productRepository.deleteById(id);
        }

}
