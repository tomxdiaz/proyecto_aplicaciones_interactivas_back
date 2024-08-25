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
import com.uade.grupo5.api_trabajo_practico.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception {
        List<ProductDTO> allProducts = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("/{title}")
    public ResponseEntity<ProductDTO> getProductByTitle(
            @PathVariable String title) throws Exception {

        ProductDTO product = productService.getProductByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO) throws Exception {

        ProductDTO product = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
