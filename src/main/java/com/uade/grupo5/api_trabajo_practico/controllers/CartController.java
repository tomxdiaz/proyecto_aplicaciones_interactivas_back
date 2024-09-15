package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    // Obtener el carrito por ID
    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    // Agregar un ítem al carrito
    @PostMapping("/{cartId}/items")
    public Cart addItemToCart(@PathVariable Long cartId, @RequestBody Item item) {
        cartService.addItemToCart(cartId, item);
        return cartService.getCartById(cartId);
    }

    // Eliminar un ítem del carrito por el ID del producto
    @DeleteMapping("/{cartId}/items/{productId}")
    public Cart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeItemFromCart(cartId, productId);
        return cartService.getCartById(cartId);
    }


}
