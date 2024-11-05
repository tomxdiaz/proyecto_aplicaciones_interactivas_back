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
import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.exceptions.CartException;
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.ResponseData;
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

  // ** TOKEN FUNCIONANDO **
  @GetMapping("")
  public ResponseEntity<ResponseData<?>> getUserCart(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      Cart cart = authUser.getCart();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(cart.toDTO()));

    } catch (UserException error) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[CartController.getUserCart] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo recuperar el carro."));
    }
  }

  // ** TOKEN FUNCIONANDO **
  @PutMapping("/product/{productId}")
  public ResponseEntity<ResponseData<?>> addProductToCart(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long productId) {
    try {

      User authUser = userService.getUserByUsername(userDetails.getUsername());

      Cart cart = authUser.getCart();

      Item addedItem = cartService.addProductToCart(cart, productId);

      ItemDTO addedItemDTO = addedItem.toDTO();

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(addedItemDTO));

    } catch (UserException | CartException | ProductException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[CartController.addItemToCart] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo agregar el item al carro"));
    }
  }

  // ** TOKEN FUNCIONANDO **
  @PutMapping("/product/{productId}/remove")
  public ResponseEntity<ResponseData<?>> removeProductFromCart(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long productId) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      Cart cart = authUser.getCart();

      cartService.removeProductFromCart(cart, productId);

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(cart.toDTO()));

    } catch (UserException | CartException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[CartController.addItemToCart] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo quitar el item del carro."));
    }
  }

  // ** TOKEN FUNCIONANDO **
  @PutMapping("/item/{productId}")
  public ResponseEntity<ResponseData<?>> removeItemFromCart(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long productId) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      Cart cart = authUser.getCart();

      cartService.removeItemFromCart(cart.getId(), productId);

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(cart.toDTO()));

    } catch (UserException | CartException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[CartController.addItemToCart] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo quitar el item del carro."));
    }
  }

  // ** TOKEN FUNCIONANDO **
  @PutMapping("/empty")
  public ResponseEntity<ResponseData<?>> emptyCart(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      Cart cart = authUser.getCart();

      cartService.emptyCart(cart.getId());

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success("Carrito vaciado correctamente!"));

    } catch (UserException | CartException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[CartController.emptyCart] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo vaciar el carro"));
    }
  }

  // ** TOKEN FUNCIONANDO **
  @PutMapping("/confirm")
  public ResponseEntity<ResponseData<?>> confirmCart(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      User authUser = userService.getUserByUsername(userDetails.getUsername());

      Cart cart = authUser.getCart();

      Buy buy = cartService.checkout(cart.getId());

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(buy));

    } catch (UserException | CartException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

    } catch (Exception error) {
      System.out.printf("[CartController.confirmCart] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo generar la compra"));
    }
  }

}
