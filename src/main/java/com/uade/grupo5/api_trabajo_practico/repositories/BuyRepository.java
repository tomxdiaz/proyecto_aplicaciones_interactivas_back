package com.uade.grupo5.api_trabajo_practico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;

public interface BuyRepository extends JpaRepository<Buy, Long>{  
  
}
