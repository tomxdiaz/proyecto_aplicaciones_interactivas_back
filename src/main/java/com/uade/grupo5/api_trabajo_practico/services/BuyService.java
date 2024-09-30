package com.uade.grupo5.api_trabajo_practico.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.BuyItem;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.BuyRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

@Service
public class BuyService {
  @Autowired
  private BuyRepository buyRepository;

  public List<Buy> getAllBuys() throws Exception {
    List<Buy> buys = buyRepository.findAll();
    return buys;
  }

  public List<Buy> getUserBuys(Long userId) throws Exception {
    return buyRepository.findByUserId(userId);
  }

  public Buy createBuy(Cart cart) throws Exception {
    Buy buy = Buy.builder()
        .buyDate(LocalDate.now())
        .user(cart.getUser())
        .build();
    List<BuyItem> buyItems = new ArrayList<>();
    cart.getItems().forEach(item -> {
      BuyItem buyItem = item.toBuyItem();
      buyItem.setBuy(buy);
      buyItems.add(buyItem);
    });
    buy.setItems(buyItems);
    Buy createdBuy = buyRepository.save(buy);
    return createdBuy;
  }

  public void deleteBuy(Long id) throws Exception {
    buyRepository.deleteById(id);
  }

}
