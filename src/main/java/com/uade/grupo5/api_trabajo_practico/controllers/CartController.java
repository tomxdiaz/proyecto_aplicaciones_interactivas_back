package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.services.CartService;

@Controller
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartService cartService;
  
  // Obtener el carrito por ID
  @GetMapping("/{cartId}")
  public ResponseEntity<?> getCartById(@PathVariable Long cartId) {
    try {
      Cart cart = cartService.getCartById(cartId);
      return ResponseEntity.status(HttpStatus.OK).body(cart.toDTO());
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // Agregar un ítem al carrito
  @PostMapping("/{cartId}/item")
  public ResponseEntity<?> addItemToCart(@PathVariable Long cartId, @RequestBody ItemDTO itemDTO) {
    try {
      Item addedItem = cartService.addItemToCart(itemDTO, cartId);
      ItemDTO addedItemDTO = addedItem.toDTO();
      return ResponseEntity.status(HttpStatus.OK).body(addedItemDTO);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // Eliminar un ítem del carrito por el ID del producto
  @DeleteMapping("/{cartId}/item/{productId}")
  public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
    try {
      cartService.removeItemFromCart(cartId, productId);
      Cart cart = cartService.getCartById(cartId);
      return ResponseEntity.status(HttpStatus.OK).body(cart.toDTO());
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // Vaciar el carrito
  @PutMapping("/{cartId}/empty")
  public ResponseEntity<?> emptyCart(@PathVariable Long cartId) {
    try {
      cartService.emptyCart(cartId);
      return ResponseEntity.status(HttpStatus.OK).body("Carrito vaciado correctamente!");
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // -devuelve precio total del carrito
  @GetMapping("/{cartId}/total")
  public ResponseEntity<?> getTotalCart(@PathVariable Long cartId){
    try {
      return ResponseEntity.ok(cartService.getTotal(cartId));
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // Obtener todos los items de un carrito
  @GetMapping("/{cartId}/items")
  public ResponseEntity<?> getItemsByCartId(@PathVariable Long cartId){
    try {
      List<Item> items = cartService.getCartById(cartId).getItems();
      return ResponseEntity.ok(items);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  @PutMapping("/{cartId}/confirm")
  public ResponseEntity<?> confirmCart(@PathVariable Long cartId){
    try {
      return ResponseEntity.status(HttpStatus.OK).body(cartService.checkout(cartId));
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }    
  }
}
