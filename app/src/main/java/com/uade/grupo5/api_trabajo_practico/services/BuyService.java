package com.uade.grupo5.api_trabajo_practico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.BuyRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyService {
  @Autowired
  private BuyRepository buyRepository;

  public List<Buy> getBuys(){
    return buyRepository.getBuys();
  }
}
