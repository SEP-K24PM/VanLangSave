package com.vls.postsaveservice.controller;

import DTO.PostElastic;
import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PostSaveController {
    private final PostService postService;
    private final RabbitMQSender rabbitMQSender;

    @Autowired
    public PostSaveController(PostService postService, RabbitMQSender rabbitMQSender) {
        this.postService = postService;
        this.rabbitMQSender = rabbitMQSender;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setStatus("Mở");
        post.setCreated_time(new Date());
        post.setVisible(true);
        Post newPost = postService.createPost(post);
        new Thread(() -> {
            PostElastic postelastic = rabbitMQSender.convertToPostElastic(newPost);
            rabbitMQSender.send(postelastic);
        }).start();
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") String postId, @RequestBody Post post) {
        Optional<Post> postData = postService.getPostDetails(UUID.fromString(postId));
        if(postData.isPresent()) {
            if(postService.checkIfAllowUpdate(postData.get())) {
                Post _post = postData.get();
                _post.setDescription(post.getDescription());
                _post.setStatus(post.getStatus());
                _post.setContact(post.getContact());
                _post.setExchange_method(post.getExchange_method());
                new Thread(() -> {
                    PostElastic postelastic = rabbitMQSender.convertToPostElastic(_post);
                    rabbitMQSender.send(postelastic);
                }).start();
                return new ResponseEntity<>(postService.updatePost(_post), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/delete/{postId}")
//    public ResponseEntity<Boolean> delete(@PathVariable("postId") UUID postId) {
//        Optional<Post> postData = postService.getPost(postId);
//        if(postData.isPresent()) {
//            Post post = postData.get();
//            if(postService.checkIfDeletePossible(post)) {
//                thingService.removePostIdFromThing(post.getThing_id());
//                postService.deletePost(post);
//                new Thread(() -> {
//                    postElasticService.delete(post);
//                }).start();
//                return new ResponseEntity<>(true, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
