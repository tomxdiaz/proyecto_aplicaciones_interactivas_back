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
                                .map(product -> new ProductDTO(
                                                product.getId(),
                                                product.getTitle(),
                                                product.getDescription(),
                                                product.getPrice(),
                                                product.getImages(),
                                                product.getAdditionalInfo(),
                                                product.getStock(),
                                                product.getCategory(),
                                                product.getFeatured())

                                )
                                .collect(Collectors.toList());
                return productsDTO;
        }

        public ProductDTO getProductById(String id) throws Exception {
                Product product = productRepository.getProductById(id);
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

                return productDTO;
        }

        public ProductDTO createProduct(
                        ProductDTO productDTO) {
                Product product = productRepository.createProduct(productDTO);
                ProductDTO newProductDTO = new ProductDTO(
                                product.getId(),
                                product.getTitle(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getImages(),
                                product.getAdditionalInfo(),
                                product.getStock(),
                                product.getCategory(),
                                product.getFeatured());
                return newProductDTO;

        }

}
