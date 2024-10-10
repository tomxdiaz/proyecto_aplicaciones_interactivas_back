package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uade.grupo5.api_trabajo_practico.dto.CategoryDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Category;
import com.uade.grupo5.api_trabajo_practico.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  // ** TOKEN FUNCIONANDO **
  @GetMapping("")
  public ResponseEntity<?> getAllCategories() {
    try {
      List<Category> allCategories = categoryService.getAllCategories();

      List<CategoryDTO> allCategoriesDTO = allCategories.stream()
          .map(Category::toDTO)
          .toList();

      return ResponseEntity.status(HttpStatus.OK).body(allCategoriesDTO);

    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

}
