package com.uade.grupo5.api_trabajo_practico.controllers;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.dto.WishListItemDTO;
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.exceptions.WishListException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.*;
import com.uade.grupo5.api_trabajo_practico.services.UserService;
import com.uade.grupo5.api_trabajo_practico.services.WishListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListItemService wishListService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUserWishList(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            User authUser = userService.getUserByUsername(userDetails.getUsername());

            List<WishListItem> wishList = wishListService.findAllWishListItemsByUserId(authUser.getId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseData.success(wishList.stream().map(WishListItem::toDTO).toList()));

        } catch (UserException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

        } catch (Exception error) {
            System.out.printf("[WishListItemController.getUserWishList] -> %s", error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseData.error("No se pudo recuperar la wishlist del usuario"));
        }

    }

    @PutMapping("")
    public ResponseEntity<?> addProductToWishList(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ProductDTO productDTO) {
        try {
            User authUser = userService.getUserByUsername(userDetails.getUsername());
            WishListItem item = wishListService.addProductToWishList(authUser, productDTO);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(item.toDTO()));

        } catch (UserException | ProductException | WishListException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseData.error("No se agregar el producto a la wishlist"));

        } catch (Exception error) {
            System.out.printf("[WishListItemController.addProductToWishList] -> %s", error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error(error.getMessage()));
        }

    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> removeProductFromWishList(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        try {
            User authUser = userService.getUserByUsername(userDetails.getUsername());
            wishListService.removeProductFromWishList(authUser, productId);
            List<WishListItemDTO> wishlist = authUser.getWishList().stream().map(WishListItem::toDTO).toList();
            return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(wishlist));

        } catch (UserException | ProductException | WishListException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));

        } catch (Exception error) {
            System.out.printf("[WishListItemController.addProductToWishList] -> %s", error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseData.error("No se pudo recuperar la wishlist del usuario"));
        }

    }

    @PutMapping("/empty")
    public ResponseEntity<?> emptyWishList(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            wishListService.emptyWishList(userService.getUserByUsername(userDetails.getUsername()));

            return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success("WishList vaciada correctamente!"));

        } catch (UserException | WishListException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.error(error.getMessage()));
        } catch (Exception error) {
            System.out.printf("[WishListItemController.emptyWishList] -> %s", error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo vaciar la wishlist del usuario");
        }

    }
}