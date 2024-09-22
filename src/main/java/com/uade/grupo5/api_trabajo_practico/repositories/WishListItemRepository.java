package com.uade.grupo5.api_trabajo_practico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.WishListItem;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long>{ 
}
