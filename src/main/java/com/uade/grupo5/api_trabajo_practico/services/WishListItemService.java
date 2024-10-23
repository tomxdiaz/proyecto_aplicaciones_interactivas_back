package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.exceptions.CartException;
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.exceptions.WishListException;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.WishListItemRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishListItemService {
  @Autowired
  private WishListItemRepository wishListItemRepository;
  @Autowired
  private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


  public List<WishListItem> findAllWishListItemsByUserId(Long userId) throws Exception {
    try {
      return wishListItemRepository.findAllByUserId(userId);

  }catch (Exception error) {
      throw new Exception("[WishListItemService.findAllWishListItemsByUserId] -> " + error.getMessage());
    }
  }


  public WishListItem addProductToWishList(User authUser , ProductDTO productDTO) throws Exception {
    // Obtener el producto por ID
    try {
      Product product = productRepository.findById(productDTO.getId())
              .orElseThrow(() -> new ProductException("Producto no encontrado"));
      List<WishListItem> wishlist = authUser.getWishList();
      boolean isProductInWishlist = wishlist.stream()
              .anyMatch(item -> item.getProduct().getId().equals(product.getId()));

      if (isProductInWishlist) {
        throw new WishListException("El producto ya está en la wishlist.");
      }

      WishListItem wishListItem = WishListItem.builder().user(authUser).product(product).build();
      wishlist.add(wishListItem);

      userRepository.save(authUser);
      return wishListItem;
    } catch (ProductException error) {
      throw new ProductException(error.getMessage());
    } catch (WishListException error) {
      throw new WishListException(error.getMessage());
    } catch (Exception error) {
      throw new Exception("[WishListItemService.addProductToWishList] -> " + error.getMessage());
    }

  }
  public void removeProductFromWishList(User authUser , Long productId) throws Exception {
    // Obtener el producto por ID
    try {
      Product product = productRepository.findById(productId)
              .orElseThrow(() -> new ProductException("Producto no encontrado"));

      List<WishListItem> wishlist = authUser.getWishList();
      WishListItem itemToRemove = wishlist.stream()
              .filter(item -> item.getProduct().getId().equals(product.getId()))
              .findFirst()
              .orElseThrow(() -> new WishListException("El producto no está en la wishlist."));


      wishlist.remove(itemToRemove);
      userRepository.save(authUser);

    } catch (ProductException error) {
      throw new ProductException(error.getMessage());
    } catch (WishListException error) {
      throw new WishListException(error.getMessage());
    } catch (Exception error) {
      throw new Exception("[WishListItemService.addProductToWishList] -> " + error.getMessage());
    }

  }

  public void emptyWishList(User authUser) throws Exception {
    try {
      authUser.getWishList().clear();
      userRepository.save(authUser);
    }
    catch (Exception error) {
      throw new Exception("[WishListItemService.emptyWishList] -> " + error.getMessage());
    }
  }






}
