package com.uade.grupo5.api_trabajo_practico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.CategoryRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() throws Exception {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.getReferenceById(id);
        return category;
    }

    public Category createCategory(
            Category category) throws Exception {

        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }

    public Category updateCategory(
            Category category) throws Exception {

        if (!categoryRepository.existsById(category.getId())) {
            throw new IllegalArgumentException("The category whit the given 'id' does not exist.");
        }

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    public void deleteProduct(Long id) throws Exception {
        categoryRepository.deleteById(id);
    }

}
