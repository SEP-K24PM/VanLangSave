package com.vls.thingservice.service;

import com.vls.thingservice.model.Category;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Thing> addCategoryNameToThing(List<Thing> list) {
        List<Thing> listWithCateName = new ArrayList<>();
        Category tmpCategory = new Category();
        for (Thing thing: list) {
            if(categoryRepository.existsById(thing.getCategory_id())) {
                tmpCategory = categoryRepository.getOne(thing.getCategory_id());
            }
            thing.setCategory_name(tmpCategory.getCategory_name());
            listWithCateName.add(thing);
        }
        return listWithCateName;
    }
}
