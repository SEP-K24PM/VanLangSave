package com.vls.postsaveservice.service;

import DTO.PostElastic;
import com.vls.postsaveservice.model.Category;
import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.model.Thing;
import com.vls.postsaveservice.repository.CategoryRepository;
import com.vls.postsaveservice.repository.ThingRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;
    private final ThingService thingService;
    private final CategoryService categoryService;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate, ThingService thingService, CategoryService categoryService) {
        this.rabbitTemplate = rabbitTemplate;
        this.thingService = thingService;
        this.categoryService = categoryService;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(PostElastic postElastic){
        rabbitTemplate.convertAndSend(exchange,routingkey, postElastic);
    }

    public PostElastic convertToPostElastic(Post post) {
        Thing thing = thingService.findThingById(post.getThing_id());
        Category category = categoryService.findCategoryById(thing.getCategory_id());

        PostElastic postElastic = new PostElastic(
                post.getId().toString(),
                post.getDescription(),
                post.getExchange_method(),
                post.getCreated_time(),
                true,
                thing.getThing_name(),
                thing.getOrigin(),
                category.getCategory_name(),
                thing.getImage()
        );
        return postElastic;
    }
}