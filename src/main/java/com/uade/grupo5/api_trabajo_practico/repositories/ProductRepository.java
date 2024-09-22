package com.uade.grupo5.api_trabajo_practico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
}
