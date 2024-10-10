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

    // ** SIRVE **
    public List<Category> getAllCategories() throws Exception {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    // ** SIRVE **
    public Category createCategory(
            Category category) throws Exception {

        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }

}
