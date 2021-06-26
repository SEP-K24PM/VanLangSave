package com.vls.postduplicationservice.service;

import com.vls.postduplicationservice.domainobject.CategoryObject;
import com.vls.postduplicationservice.domainobject.PostObject;
import com.vls.postduplicationservice.domainobject.ThingObject;
import com.vls.postduplicationservice.model.Category;
import com.vls.postduplicationservice.model.Post;
import com.vls.postduplicationservice.model.Thing;
import com.vls.postduplicationservice.repository.PostRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService implements RabbitListenerConfigurer {

    private final PostRepository postRepository;

    @Autowired
    public RabbitMQService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "post_queue")
    public void receivedMessage(PostObject postObject) {
        saveIntoElastic(postObject);
    }

    private void saveIntoElastic(PostObject postObject) {
        Post post = convertToPost(postObject);
        postRepository.save(post);
    }

    private Post convertToPost(PostObject postObject) {
        Post post = new Post(postObject.getId(),
                postObject.getDescription(),
                postObject.getExchange_methods(),
                postObject.getCreated_time(),
                convertToThing(postObject.getThing()));
        return post;
    }

    private Thing convertToThing(ThingObject thingObject) {
        Thing thing = new Thing(
                thingObject.getId(),
                thingObject.getThing_name(),
                thingObject.getOrigin(),
                thingObject.getPrice(),
                thingObject.getQuantity(),
                thingObject.getUsed_time(),
                convertToCategory(thingObject.getCategoryObject())
        );
        return thing;
    }

    private Category convertToCategory(CategoryObject categoryObject) {
        Category category = new Category(
                categoryObject.getId(),
                categoryObject.getCategory_name()
        );
        return category;
    }
}
