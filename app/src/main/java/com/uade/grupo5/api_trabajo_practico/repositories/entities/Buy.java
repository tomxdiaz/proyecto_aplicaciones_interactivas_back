package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import org.springframework.data.annotation.Id;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Builder
public class Buy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long chartId;
  private String buyDate;


  public BuyDTO toDTO(Buy buy){
    return BuyDTO.builder()
            .chartId(buy.getChartId())
            .buyDate(buy.getBuyDate())
            .build();
  }

}