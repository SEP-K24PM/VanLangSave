package com.vls.postsaveservice.controller;

import com.vls.postsaveservice.dto.postelastic;
import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PostSaveController {

    private final PostService postService;
    private final RabbitMQSender rabbitMQSender;
    private final ThingService thingService;

    @Autowired
    public PostSaveController(PostService postService, RabbitMQSender rabbitMQSender, ThingService thingService) {
        this.postService = postService;
        this.rabbitMQSender = rabbitMQSender;
        this.thingService = thingService;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        if(postService.checkThingIsAvailable(post.getThing_id())) {
            post.setStatus("Mở");
            post.setCreated_time(new Date());
            post.setVisible(true);
            Post newPost = postService.createPost(post);
            new Thread(() -> {
                postelastic postelastic = rabbitMQSender.convertToPostElastic(newPost);
                rabbitMQSender.send(postelastic);
                thingService.updateThingWithNewPost(post.getThing_id(), post.getId());
            }).start();
            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
