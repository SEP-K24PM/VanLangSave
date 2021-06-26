package com.vls.postsaveservice.service;

import com.vls.postsaveservice.model.Category;
import com.vls.postsaveservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryById(UUID id) {
        return categoryRepository.findCategoryById(id);
    }
}
