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
        public Product createProduct(Product product) throws Exception {
          try{
            Product createdProduct = productRepository.save(product);
            return createdProduct;
          }catch(Exception error){
            throw new Exception("[ProductService.createProduct] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public List<Product> getAllProducts() throws Exception {
          try{
            List<Product> products = productRepository.findAll();
            return products;
          }catch(Exception error){
            throw new Exception("[ProductService.getAllProducts] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public Product getProductById(Long id) throws Exception {
          try{
            Product product = productRepository.getReferenceById(id);
            return product;
          }catch(Exception error){
            throw new Exception("[ProductService.getProductById] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public Product updateProduct(Product product) throws Exception {
          try{
            if (!productRepository.existsById(product.getId())) {
              throw new IllegalArgumentException("The product with the given 'id' does not exist.");
            }

            Product updatedProduct = productRepository.save(product);
            return updatedProduct;
          }catch(Exception error){
            throw new Exception("[ProductService.updateProduct] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public void deleteProduct(Long id) throws Exception {
          try{
            productRepository.deleteById(id);
          }catch(Exception error){
            throw new Exception("[ProductService.deleteProduct] -> " + error.getMessage());
          }
        }

}
