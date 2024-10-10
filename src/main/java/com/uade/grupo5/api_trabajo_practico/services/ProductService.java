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

        // ** SIRVE **
        public Product createProduct(
                        Product product) throws Exception {

                Product createdProduct = productRepository.save(product);
                return createdProduct;
        }

        // ** SIRVE **
        public List<Product> getAllProducts() throws Exception {
                List<Product> products = productRepository.findAll();
                return products;
        }

        // ** SIRVE **
        public Product getProductById(Long id) throws Exception {
                Product product = productRepository.getReferenceById(id);
                return product;
        }

        // ** SIRVE **
        public Product updateProduct(
                        Product product) throws Exception {

                if (!productRepository.existsById(product.getId())) {
                        throw new IllegalArgumentException("The product with the given 'id' does not exist.");
                }

                Product updatedProduct = productRepository.save(product);
                return updatedProduct;
        }

        // ** SIRVE **
        public void deleteProduct(Long id) throws Exception {
                productRepository.deleteById(id);
        }

}
