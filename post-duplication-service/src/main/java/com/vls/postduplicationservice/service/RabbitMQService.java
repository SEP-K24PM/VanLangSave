package com.vls.postduplicationservice.service;

import com.vls.postduplicationservice.Model.postelastic;
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
    public void receivedMessage(postelastic post) {
        postRepository.save(post);
    }

    @RabbitListener(queues = "delete_queue")
    public void receivedDeleteMessage(postelastic post) {
        postRepository.delete(post);
    }

}
