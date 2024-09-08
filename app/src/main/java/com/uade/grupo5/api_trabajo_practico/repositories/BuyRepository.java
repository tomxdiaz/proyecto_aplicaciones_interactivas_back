package com.uade.grupo5.api_trabajo_practico.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;


import java.util.List;

@Repository
public class BuyRepository {
  @Autowired
  private List<Buy> buys;

  public BuyRepository(){
    buys.add(new Buy(1, 12, "20240901"));
    buys.add(new Buy(2, 9, "20240903"));
  }

  public void addBuy(BuyDTO buy){
    buys.add(Buy.builder().chartId(buy.getChartId()).buyDate(buy.getBuyDate()).build());
  }

  public List<Buy> getBuys(){
    return buys;
  }
}
