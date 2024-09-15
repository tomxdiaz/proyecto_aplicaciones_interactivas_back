package com.uade.grupo5.api_trabajo_practico.services;

import com.uade.grupo5.api_trabajo_practico.repositories.CartRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void addItemToCart(Long cartId, Item newItem) {
        Cart cart = getCartById(cartId);
        // Verificar si el producto ya existe en el carrito y actualizar cantidad
        Optional<Item> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(newItem.getProduct().getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + newItem.getQuantity());
        } else {
            newItem.setCart(cart); // Establecer la relación con el carrito
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
    }

    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = getCartById(cartId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }


}
