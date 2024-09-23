package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.dto.BuyDTO;
import com.uade.grupo5.api_trabajo_practico.dto.CategoryDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Category;
import com.uade.grupo5.api_trabajo_practico.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
      try{
        List<Category> allCategories = categoryService.getAllCategories();

        List<CategoryDTO> allCategoriesDTO = allCategories.stream()
                .map(category -> category.toDTO())
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(allCategoriesDTO);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @RequestBody CategoryDTO categoryDTO) {
        try{
          // The 'id' field must be null in order to create a new category
          categoryDTO.setId(null);

          Category category = categoryDTO.toEntity();

          Category createdCategory = categoryService.createCategory(category);

          CategoryDTO createdCategoryDTO = createdCategory.toDTO();
          return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryDTO);
        }catch(Exception error){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @RequestBody CategoryDTO categoryDTO) {
      try{
        Category category = categoryDTO.toEntity();

        Category updatedCategory = categoryService.updateCategory(category);

        CategoryDTO updatedCategoryDTO = updatedCategory.toDTO();
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDTO);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable Long id) {
      try{
        categoryService.deleteCategory(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
      }catch(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
      }
    }

}
