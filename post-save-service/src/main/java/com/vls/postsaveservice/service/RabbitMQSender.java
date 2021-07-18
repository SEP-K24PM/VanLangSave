package com.vls.postsaveservice.service;

import DTO.PostDTO;
import DTO.PostElastic;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.deleteroutingkey}")
    private String deleteroutingkey;

    public void send(PostElastic postElastic){
        rabbitTemplate.convertAndSend(exchange, routingkey, postElastic);
    }

    public void sendDelete(PostElastic postElastic) {
        rabbitTemplate.convertAndSend(exchange, deleteroutingkey, postElastic);
    }

    public PostElastic convertToPostElastic(PostDTO post) {
        PostElastic postElastic = new PostElastic(
                post.getId().toString(),
                post.getDescription(),
                post.getExchange_method(),
                post.getCreated_time(),
                true,
                post.getThing().getThing_name(),
                post.getThing().getOrigin(),
                post.getThing().getCategory().getCategory_name(),
                post.getThing().getImage()
        );
        return postElastic;
    }

}