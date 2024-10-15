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
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.ResponseData;
import com.uade.grupo5.api_trabajo_practico.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductService productService;

  // ** SIRVE **
  @PostMapping("")
  public ResponseEntity<ResponseData<?>> createProduct(@RequestBody ProductDTO productDTO) {
    try {
      productDTO.setId(null);

      Product product = productDTO.toEntity();

      Product createdProduct = productService.createProduct(product);

      ProductDTO createdProductDTO = createdProduct.toDTO();

      return ResponseEntity.status(HttpStatus.CREATED).body(ResponseData.success(createdProductDTO));

    } catch (Exception error) {
      System.out.printf("[ProductController.createProduct] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo crear el producto"));
    }
  }

  // ** SIRVE **
  @PutMapping("")
  public ResponseEntity<ResponseData<?>> updateProduct(@RequestBody ProductDTO productDTO) {
    try {
      Product product = productDTO.toEntity();

      Product updatedProduct = productService.updateProduct(product);

      ProductDTO updatedProductDTO = updatedProduct.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(updatedProductDTO));

    }catch (ProductException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[ProductController.updateProduct] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo actualizar el producto"));
    }
  }

  // ** SIRVE **
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<?>> deleteProduct(@PathVariable Long id) {
    try {
      productService.deleteProduct(id);

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(null));

    } catch (Exception error) {
      System.out.printf("[ProductController.deleteProduct] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudo eliminar el producto"));
    }
  }

  // ** SIRVE **
  @GetMapping("")
  public ResponseEntity<ResponseData<?>> getAllProducts() {
    try {
      List<Product> allProducts = productService.getAllProducts();

      List<ProductDTO> allProductsDTO = allProducts.stream()
                      .map(Product::toDTO)
                      .toList();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(allProductsDTO));

    } catch (Exception error) {
      System.out.printf("[ProductController.getAllProducts] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se pudieron recuperar los productos"));
    }
  }

  // ** SIRVE **
  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<?>> getProductById(@PathVariable Long id) {
    try {
      Product product = productService.getProductById(id);

      ProductDTO productDTO = product.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(productDTO));

    } catch (Exception error) {
      System.out.printf("[ProductController.getProductById] -> %s", error.getMessage() );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error("No se encontro el producto"));
    }
  }
}
