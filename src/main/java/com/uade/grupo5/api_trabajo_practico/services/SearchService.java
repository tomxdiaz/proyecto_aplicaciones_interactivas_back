package com.uade.grupo5.api_trabajo_practico.services;

import java.time.LocalDateTime;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Search;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserRepository userRepository;

  public List<Search> findAllSearchesByUserId(Long userId) throws Exception {
    try {
      return userRepository.findById(userId).get().getLastSearches();
    } catch (Exception error) {
      throw new Exception("[SearchItemService.findAllSearchesByUserId] -> " + error.getMessage());
    }
  }

  public Search addSearch(User authUser, ProductDTO productDTO) throws Exception {
    try {
      Product product = productRepository.findById(productDTO.getId())
          .orElseThrow(() -> new ProductException("Producto no encontrado"));

      List<Search> searches = authUser.getLastSearches();
      if (searches.size() >= 10) {
        searches.remove(0);
      }

      Search search = Search.builder().user(authUser).product(product).date(LocalDateTime.now()).build();
      searches.add(search);
      userRepository.save(authUser);
      return search;
    } catch (ProductException error) {
      throw new ProductException(error.getMessage());
    } catch (Exception error) {
      throw new Exception("[SearchItemService.addSearch] -> " + error.getMessage());
    }
  }

  public void emptySearches(User authUser) throws Exception {
    try {
      authUser.getLastSearches().clear();
      userRepository.save(authUser);
    } catch (Exception error) {
      throw new Exception("[SearchItemService.emptySearches] -> " + error.getMessage());
    }
  }
}
