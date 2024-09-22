package com.uade.grupo5.api_trabajo_practico.services;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.CartRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart createCart(Long userID) {
        if (userID == null) {
            throw new IllegalArgumentException("userID cannot be null");
        }

        Cart newCart = new Cart();
        newCart.setUserID(userID);
        return cartRepository.save(newCart);
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void addItemToCart(Long cartId, ItemDTO itemDTO) {
        // Obtener el carrito por ID
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear el nuevo ítem
        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(itemDTO.getQuantity());
        item.setCart(cart);

        // Agregar el ítem al carrito
        cart.getItems().add(item);

        // Guardar el carrito actualizado
        cartRepository.save(cart);
    }


    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = getCartById(cartId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    public void removeCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }


}
