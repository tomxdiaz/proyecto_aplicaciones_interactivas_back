package com.uade.grupo5.api_trabajo_practico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Long> {
  List<Buy> findByUserId(Long userId);
}
