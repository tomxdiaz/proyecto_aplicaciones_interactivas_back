package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<?> addItemToCart(@RequestBody ItemDTO itemDTO) {
    try {
      Item addedItem = cartService.addItemToCart(itemDTO);
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
  public ResponseEntity<Double> getTotalCart(@PathVariable Long cartId) throws Exception {
    return ResponseEntity.ok(cartService.getTotal(cartId));
  }

  // Obtener todos los items de un carrito
  @GetMapping("/{cartId}/items")
  public ResponseEntity<List<Item>> getItemsByCartId(@PathVariable Long cartId) throws Exception {
    List<Item> items = cartService.getCartById(cartId).getItems();
    return ResponseEntity.ok(items);
  }
}
