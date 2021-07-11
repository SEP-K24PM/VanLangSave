package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.model.Category;
import com.vls.newsfeedservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategory(UUID cateId) {
        return categoryRepository.findById(cateId);
    }
}
