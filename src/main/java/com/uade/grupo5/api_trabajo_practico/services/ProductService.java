package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.CartRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.SearchRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.WishListItemRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private CartRepository cartRepository;

        @Autowired
        private WishListItemRepository wishListItemRepository;

        @Autowired
        private SearchRepository searchRepository;
        // ** SIRVE **
        public Product createProduct(Product product) throws Exception {
          try {
            Product createdProduct = productRepository.save(product);
            return createdProduct;
          } catch (Exception error) {
            throw new Exception("[ProductService.createProduct] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public List<Product> getAllProducts() throws Exception {
          try {
            List<Product> products = productRepository.findAll();
            return products;
          } catch (Exception error) {
            throw new Exception("[ProductService.getAllProducts] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public Product getProductById(Long id) throws Exception {
          try {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("producto no encontrado"));
            return product;
          } catch (Exception error) {
            throw new Exception("[ProductService.getProductById] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        public Product updateProduct(Product product) throws Exception {
          try {
            if (!productRepository.existsById(product.getId())) 
              throw new ProductException("El producto con id: '" + product.getId() + "' no existe.");
            
            Product updatedProduct = productRepository.save(product);
            return updatedProduct;
          } catch (ProductException error) {
            throw new ProductException(error.getMessage());
          } catch (Exception error) {
            throw new Exception("[ProductService.updateProduct] -> " + error.getMessage());
          }
        }

        // ** SIRVE **
        @Transactional
        public void deleteProduct(Long id) throws Exception {
          try {

              List<Cart> carts = cartRepository.findAll();
              for (Cart cart : carts) {
                  cart.getItems().removeIf(item -> item.getProduct().getId().equals(id));
              }
              cartRepository.saveAll(carts);

              searchRepository.deleteByProductId(id);
              wishListItemRepository.deleteByProductId(id);
              productRepository.deleteById(id);
          } catch (Exception error) {
            throw new Exception("[ProductService.deleteProduct] -> " + error.getMessage());
          }
        }

}
