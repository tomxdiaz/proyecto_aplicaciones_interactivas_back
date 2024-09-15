package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.dto.WishListItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.WishListItemRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.WishListItem;

@Service
public class WishListItemService {
  @Autowired
  private WishListItemRepository wishListItemRepository;

  public List<WishListItemDTO> findAllWishList(){
    List<WishListItem> wishList = wishListItemRepository.findAll();
    return wishList.stream().map(WishListItem::toDTO).toList();
  }
}
