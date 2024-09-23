package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    // Obtener el carrito por ID
    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable Long cartId) {
      try{
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }

    // Crear carrito (invocado al crear usuario - pasa ID por parametro)
    @PostMapping("")
    public ResponseEntity<?> createCart(@RequestBody Long userID) {
      try{
        Cart cart =  cartService.createCart(userID);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }

    // Agregar un ítem al carrito
    @PostMapping("/{cartId}/item")
    public ResponseEntity<?> addItemToCart(@PathVariable Long cartId, @RequestBody ItemDTO itemDTO) {
      try{
        cartService.addItemToCart(cartId, itemDTO);
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      } 
    }

    // Eliminar un ítem del carrito por el ID del producto
    @DeleteMapping("/{cartId}/item/{productId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
      try{
        cartService.removeItemFromCart(cartId, productId);
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }
    // Eliminar un ítem del carrito por el ID del producto
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> removeCart(@PathVariable Long cartId) {
      try{
        cartService.removeCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body("Carrito eliminado correctamente!");
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }


}
