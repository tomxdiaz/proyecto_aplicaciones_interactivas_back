package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
        @Autowired
        private ProductService productService;

        // SIRVE
        @PostMapping("")
        public ResponseEntity<?> createProduct(
                        @RequestBody ProductDTO productDTO) {
                try {
                        // The 'id' field must be null in order to create a new product
                        productDTO.setId(null);

                        Product product = productDTO.toEntity();

                        Product createdProduct = productService.createProduct(product);

                        ProductDTO createdProductDTO = createdProduct.toDTO();

                        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

        }

        // SIRVE
        @PutMapping("")
        public ResponseEntity<?> updateProduct(
                        @RequestBody ProductDTO productDTO) throws Exception {
                try {
                        Product product = productDTO.toEntity();

                        Product updatedProduct = productService.updateProduct(product);

                        ProductDTO updatedProductDTO = updatedProduct.toDTO();

                        return ResponseEntity.status(HttpStatus.OK).body(updatedProductDTO);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

        }

        // SIRVE
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteProduct(
                        @PathVariable Long id) throws Exception {
                try {
                        productService.deleteProduct(id);

                        return ResponseEntity.status(HttpStatus.OK).body(null);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }
        }

        // SIRVE
        @GetMapping("")
        public ResponseEntity<?> getAllProducts() {
                try {
                        List<Product> allProducts = productService.getAllProducts();

                        List<ProductDTO> allProductsDTO = allProducts.stream()
                                        .map(product -> product.toDTO())
                                        .toList();

                        return ResponseEntity.status(HttpStatus.OK).body(allProductsDTO);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

        }

        // SIRVE
        @GetMapping("/{id}")
        public ResponseEntity<?> getProductById(
                        @PathVariable Long id) throws Exception {
                try {
                        Product product = productService.getProductById(id);

                        ProductDTO productDTO = product.toDTO();

                        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

        }

        // TODO
        // @GetMapping("/category/{categoryId}")
        // public ResponseEntity<?> getAllProductsByCategory(
        // @PathVariable Long categoryId) {
        // try {
        // List<Product> allProducts =
        // productService.getAllProductsByCategoryId(categoryId);

        // List<ProductDTO> allProductsDTO = allProducts.stream()
        // .map(product -> product.toDTO())
        // .toList();

        // return ResponseEntity.status(HttpStatus.OK).body(allProductsDTO);
        // } catch (Exception e) {
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        // }
        // }

}
