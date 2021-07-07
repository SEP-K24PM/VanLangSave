package com.vls.postupdateservice.service;

import com.vls.postupdateservice.dto.postelastic;
import com.vls.postupdateservice.model.Category;
import com.vls.postupdateservice.model.Post;
import com.vls.postupdateservice.model.Thing;
import com.vls.postupdateservice.repository.PostElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostElasticService {
    private final PostElasticRepository postElasticRepository;
    private final ThingService thingService;
    private final CategoryService categoryService;

    @Autowired
    public PostElasticService(PostElasticRepository postElasticRepository, ThingService thingService, CategoryService categoryService) {
        this.postElasticRepository = postElasticRepository;
        this.thingService = thingService;
        this.categoryService = categoryService;
    }

    public postelastic update(Post _post) {
        Thing thing = thingService.findThingById(_post.getThing_id());
        Category category = categoryService.findCategoryById(thing.getCategory_id());
        postelastic postelastic = new postelastic(_post.getId().toString(), _post.getDescription(),
                _post.getExchange_method(), _post.getCreated_time(), _post.isVisible(),
                thing.getThing_name(), thing.getOrigin(), category.getCategory_name());
        return postElasticRepository.save(postelastic);
    }
}