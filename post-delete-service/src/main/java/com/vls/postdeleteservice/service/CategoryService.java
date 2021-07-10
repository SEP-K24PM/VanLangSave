package com.vls.postdeleteservice.service;

import com.vls.postdeleteservice.model.Category;
import com.vls.postdeleteservice.repository.CategoryRepository;
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
