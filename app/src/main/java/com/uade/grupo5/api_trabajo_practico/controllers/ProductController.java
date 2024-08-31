package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception {
        List<Product> allProducts = productService.getAllProducts();

        List<ProductDTO> allProductsDTO = allProducts.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getTitle(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImages(),
                        product.getAdditionalInfo(),
                        product.getStock(),
                        product.getCategory(),
                        product.getFeatured()))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(allProductsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable String id) throws Exception {

        Product product = productService.getProductById(id);
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getImages(),
                product.getAdditionalInfo(),
                product.getStock(),
                product.getCategory(),
                product.getFeatured());
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productData) throws Exception {
        Product product = productService.createProduct(productData);
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getImages(),
                product.getAdditionalInfo(),
                product.getStock(),
                product.getCategory(),
                product.getFeatured());
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

}
