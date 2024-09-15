package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.BuyRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

@Service
public class BuyService {
  @Autowired
  private BuyRepository buyRepository;

  public List<BuyDTO> getBuys() throws Exception{
    List<Buy> buys = buyRepository.findAll();
    return buys.stream().map(Buy::toDTO).toList();
  }
}
