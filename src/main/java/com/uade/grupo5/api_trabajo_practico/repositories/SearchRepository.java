package com.uade.grupo5.api_trabajo_practico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Search;

public interface SearchRepository extends JpaRepository<Search, Long> {
    public List<Search> findAllByUserId(Long userId);
    void deleteByProductId(Long id);
}
