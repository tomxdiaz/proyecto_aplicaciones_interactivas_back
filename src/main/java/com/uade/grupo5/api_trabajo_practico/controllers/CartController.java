package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
  @Autowired
  private CartService cartService;

  // TODO -> INFO DEL TOKEN
  @GetMapping("/{cartId}")
  public ResponseEntity<?> getUserCart(@PathVariable Long cartId) {
    try {
      Cart cart = cartService.getCartById(cartId);
      return ResponseEntity.status(HttpStatus.OK).body(cart.toDTO());
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO -> INFO DEL TOKEN
  @PutMapping("/{cartId}/item")
  public ResponseEntity<?> addItemToCart(@PathVariable Long cartId, @RequestBody ItemDTO itemDTO) {
    try {
      Item addedItem = cartService.addItemToCart(itemDTO, cartId);
      ItemDTO addedItemDTO = addedItem.toDTO();
      return ResponseEntity.status(HttpStatus.OK).body(addedItemDTO);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO -> INFO DEL TOKEN
  @PutMapping("/{cartId}/item/{productId}")
  public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
    try {
      cartService.removeItemFromCart(cartId, productId);
      Cart cart = cartService.getCartById(cartId);
      return ResponseEntity.status(HttpStatus.OK).body(cart.toDTO());
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO -> INFO DEL TOKEN
  @PutMapping("/{cartId}/empty")
  public ResponseEntity<?> emptyCart(@PathVariable Long cartId) {
    try {
      cartService.emptyCart(cartId);
      return ResponseEntity.status(HttpStatus.OK).body("Carrito vaciado correctamente!");
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO -> INFO DEL TOKEN
  @PutMapping("/{cartId}/confirm")
  public ResponseEntity<?> confirmCart(@PathVariable Long cartId) {
    try {
      Buy buy = cartService.checkout(cartId);
      return ResponseEntity.status(HttpStatus.OK).body(buy);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // TODO -> EN PRINCIPIO VUELA, EL TOTAL YA VIENE EN EL DTO
  // @GetMapping("/{cartId}/total")
  // public ResponseEntity<?> getTotalCart(@PathVariable Long cartId) {
  // try {
  // return ResponseEntity.ok(cartService.getTotalPrice(cartId));
  // } catch (Exception error) {
  // return
  // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
  // }
  // }

}
