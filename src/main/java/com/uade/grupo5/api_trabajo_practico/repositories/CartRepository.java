package com.uade.grupo5.api_trabajo_practico.repositories;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
