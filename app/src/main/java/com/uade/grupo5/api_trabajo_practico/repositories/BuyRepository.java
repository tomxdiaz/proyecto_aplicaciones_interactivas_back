package com.uade.grupo5.api_trabajo_practico.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

@Repository
public class BuyRepository {
  private final List<Buy> buys = new ArrayList<>();

  public BuyRepository(){
    buys.add(Buy.builder().cartId(12L).buyDate("20240901").build());
    buys.add(Buy.builder().cartId(9L).buyDate("20240903").build());
  }

  public List<Buy> getBuys(){
    return buys;
  }
}
