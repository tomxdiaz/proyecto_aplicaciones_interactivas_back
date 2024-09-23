package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.dto.CartDTO;
import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    // Obtener el carrito por ID
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        CartDTO cartDTO = cart.toDTO();
        return ResponseEntity.ok(cartDTO);
    }

    // -devuelve precio total del carrito
    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> getTotalCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getTotal(cartId));
    }


    // Agregar un ítem al carrito
    @PostMapping("/{cartId}/items")
    public ResponseEntity<ItemDTO> addItemToCart(@PathVariable Long cartId, @RequestBody ItemDTO itemDTO) {
        Item addedItem = cartService.addItemToCart(cartId, itemDTO);
        ItemDTO addedItemDTO = addedItem.toDTO();
        return ResponseEntity.ok(addedItemDTO);
    }


    // Obtener todos los items de un carrito
    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<Item>> getItemsByCart(@PathVariable Long cartId) {
        List<Item> items = cartService.getCartById(cartId).getItems();
        return ResponseEntity.ok(items);
    }

    // Eliminar un ítem del carrito por el ID del producto
    @DeleteMapping("/{cartId}/items/{productId}")
    public Cart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeItemFromCart(cartId, productId);
        return cartService.getCartById(cartId);
    }
//     Eliminar el carrito de un usuario
//    @PutMapping("/{cartId}")
//    public void removeCart(@PathVariable Long cartId) {
//        cartService.emptyCart(cartId);
//    }
//
//     Crear carrito (invocado al crear usuario - pasa ID por parametro)
//    @PostMapping("")
//    public Cart createCart(@RequestBody Long userID) {
//        return cartService.createCart(userID);
//    }


}
