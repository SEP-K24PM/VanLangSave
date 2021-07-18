package com.vls.manager_statisticservice.service;

import com.vls.manager_statisticservice.model.Category;
import com.vls.manager_statisticservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    public List<Category> getAllCategory(){
//        List<Category> list = new ArrayList<>();
//        categoryRepository.countAllCategory().forEach(list::add);
//        return list;
//    }


    @Query(value = "SELECT\n" +
            "    Category.id, Category.category_name,\n" +
            "    (SELECT COUNT(*) FROM thing t WHERE t.category_id = Category.id) \n" +
            "  \n" +
            "FROM\n" +
            "    Category ", nativeQuery = true)
    public List<Category> countAllCategory() {
        return categoryRepository.countAllCategory();
    }
}
