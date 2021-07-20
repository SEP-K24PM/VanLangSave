package com.vls.managementservice.service;

import com.vls.managementservice.model.Category;
import com.vls.managementservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> countAllCategory() {
        return categoryRepository.countAllCategory();
    }
}
