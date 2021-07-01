package com.vls.postsaveservice.service;

import com.vls.postsaveservice.dto.postelastic;
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
    private final ThingRepository thingRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate, ThingRepository thingRepository, CategoryRepository categoryRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.thingRepository = thingRepository;
        this.categoryRepository = categoryRepository;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(postelastic postElastic){
        rabbitTemplate.convertAndSend(exchange,routingkey, postElastic);
    }

    public postelastic convertToPostElastic(Post post) {
        Thing thing = thingRepository.findThingById(post.getThing_id());
        Category category = categoryRepository.findCategoryById(thing.getCategory_id());

        postelastic postElastic = new postelastic(
                post.getId().toString(),
                post.getDescription(),
                post.getExchange_method(),
                post.getCreated_time(),
                true,
                thing.getThing_name(),
                thing.getOrigin(),
                category.getCategory_name()
        );
        return postElastic;
    }
}