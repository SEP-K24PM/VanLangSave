package com.vls.postsaveservice.service;

import com.vls.postsaveservice.domainobject.CategoryObject;
import com.vls.postsaveservice.domainobject.PostObject;
import com.vls.postsaveservice.domainobject.ThingObject;
import com.vls.postsaveservice.model.Category;
import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.model.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainObjectService {

    private final ThingService thingService;
    private final CategoryService categoryService;

    @Autowired
    public DomainObjectService(ThingService thingService, CategoryService categoryService) {
        this.thingService = thingService;
        this.categoryService = categoryService;
    }

    public PostObject convertToPostOject(Post post) {
        Thing thing = thingService.findThingById(post.getThing_id());
        Category category = categoryService.findCategoryById(thing.getCategory_id());

        CategoryObject categoryObject = converToCategoryObject(category);
        ThingObject thingObject = convertToThingObject(thing, categoryObject);

        PostObject postObject = new PostObject(
                post.getId(),
                post.getDescription(),
                post.getExchange_method(),
                post.getCreated_time(),
                thingObject);

        return postObject;
    }

    private CategoryObject converToCategoryObject(Category category) {
        CategoryObject categoryObject = new CategoryObject(
                category.getId(),
                category.getCategory_name()
        );
        return categoryObject;
    }

    private ThingObject convertToThingObject(Thing thing, CategoryObject categoryObject) {
        ThingObject thingObject = new ThingObject(
                thing.getId(),
                thing.getThing_name(),
                thing.getOrigin(),
                thing.getPrice(),
                thing.getQuantity(),
                thing.getUsed_time(),
                categoryObject
        );
        return thingObject;
    }
}
