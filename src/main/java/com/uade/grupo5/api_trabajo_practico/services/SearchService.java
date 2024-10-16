package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.SearchRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Search;

@Service
public class SearchService {
  @Autowired
  private SearchRepository searchRepository;

  public List<Search> getAllSearchesByUserId(Long userId) throws Exception {
    try {
      return searchRepository.findAllByUserId(userId);
    } catch (Exception error) {
      throw new Exception("[SearchService.getAllSearchesByUserId] -> " + error.getMessage());
    }
  }
}
