package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.CartService;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
  @Autowired
  private CartService cartService;

  @Autowired
  private UserService userService;

  // SIRVE
  @GetMapping("")
  public ResponseEntity<?> getUserCart(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());
      Cart cart = authUser.getCart();

      return ResponseEntity.status(HttpStatus.OK).body(cart.toDTO());
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // SIRVE
  @PutMapping("/item")
  public ResponseEntity<?> addItemToCart(@AuthenticationPrincipal UserDetails userDetails,
      @RequestBody ItemDTO itemDTO) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());
      Cart cart = authUser.getCart();
      Item addedItem = cartService.addItemToCart(itemDTO, cart.getId());
      ItemDTO addedItemDTO = addedItem.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(addedItemDTO);
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // SIRVE
  @PutMapping("/item/{productId}")
  public ResponseEntity<?> removeItemFromCart(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long productId) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());
      Cart cart = authUser.getCart();
      cartService.removeItemFromCart(cart.getId(), productId);

      return ResponseEntity.status(HttpStatus.OK).body(cart.toDTO());
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // SIRVE
  @PutMapping("/empty")
  public ResponseEntity<?> emptyCart(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());
      Cart cart = authUser.getCart();
      cartService.emptyCart(cart.getId());
      return ResponseEntity.status(HttpStatus.OK).body("Carrito vaciado correctamente!");
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

  // SIRVE
  @PutMapping("/confirm")
  public ResponseEntity<?> confirmCart(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());
      Cart cart = authUser.getCart();
      Buy buy = cartService.checkout(cart.getId());
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
