package com.vls.postduplicationservice.service;

import com.vls.postduplicationservice.domainobject.PostObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "post_queue")
    public void receivedMessage(PostObject postObject) {
        System.out.println("received post: "+postObject);
    }
}
