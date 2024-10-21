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
      List<WishListItem> wishlist = wishListItemRepository.findAllByUserId(userId);
      if (wishlist.isEmpty()) {
        throw new WishListException("La wishlist esta vacía!");
      }
      return wishlist;
    }catch (WishListException error) {
      throw new WishListException(error.getMessage());
    }
    catch (Exception error) {
      throw new Exception("[WishListItemService.findAllWishListItemsByUserId] -> " + error.getMessage());
    }
  }


  public WishListItem addProductToWishList(User authUser , ProductDTO productDTO) throws Exception {
    // Obtener el producto por ID
    try {
      Product product = productRepository.findById(productDTO.getId())
              .orElseThrow(() -> new ProductException("Producto no encontrado"));
      WishListItem wishListItem = WishListItem.builder().user(authUser).product(product).build();
      authUser.getWishList().add(wishListItem);
      userRepository.save(authUser);
      return wishListItem;
    } catch (ProductException error) {
      throw new ProductException(error.getMessage());
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
