package com.vls.postsaveservice.controller;

import DTO.PostDTO;
import DTO.PostElastic;
import com.vls.postsaveservice.model.*;
import com.vls.postsaveservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PostSaveController {
    private final PostService postService;
    private final RabbitMQSender rabbitMQSender;
    private final ThingService thingService;
    private final CategoryService categoryService;

    @Autowired
    public PostSaveController(PostService postService, RabbitMQSender rabbitMQSender, ThingService thingService,
            CategoryService categoryService) {
        this.postService = postService;
        this.rabbitMQSender = rabbitMQSender;
        this.categoryService = categoryService;
        this.thingService = thingService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        Thing thing = thingService.findThingById(newPost.getThing_id());
        Category category = categoryService.findCategoryById(thing.getCategory_id());
        PostDTO postDTO = postService.convertToPostDTO(newPost, thing, category);
        new Thread(() -> {
            PostElastic postElastic = rabbitMQSender.convertToPostElastic(postDTO);
            rabbitMQSender.send(postElastic);
        }).start();
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("postId") String postId, @RequestBody Post post) {
        Optional<Post> postData = postService.getPostDetails(UUID.fromString(postId));
        if (postData.isPresent()) {
            if (postService.checkIfAllowUpdate(postData.get())) {
                Post _post = postData.get();
                _post.setDescription(post.getDescription());
                _post.setStatus(post.getStatus());
                _post.setContact(post.getContact());
                _post.setExchange_method(post.getExchange_method());

                Post updatedPost = postService.updatePost(_post);
                Thing thing = thingService.findThingById(updatedPost.getThing_id());
                Category category = categoryService.findCategoryById(thing.getCategory_id());
                PostDTO postDTO = postService.convertToPostDTO(updatedPost, thing, category);

                new Thread(() -> {
                    PostElastic postelastic = rabbitMQSender.convertToPostElastic(postDTO);
                    rabbitMQSender.send(postelastic);
                }).start();
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/delete/{postId}")
    public ResponseEntity<Boolean> delete(@PathVariable("postId") UUID postId) {
        Optional<Post> postData = postService.getPostDetails(postId);
        if (postData.isPresent()) {
            Post post = postData.get();
            if (postService.checkIfDeletePossible(post)) {
                postService.deletePost(post);
                new Thread(() -> {
                    PostElastic postElastic = new PostElastic();
                    postElastic.setId(post.getId().toString());
                    rabbitMQSender.sendDelete(postElastic);
                }).start();
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cancel/{postId}")
    public ResponseEntity<Post> cancel(@PathVariable("postId") UUID postId) {
        Optional<Post> postData = postService.getPostDetails(postId);
        if (postData.isPresent()) {
            return new ResponseEntity<>(postService.cancelPost(postData.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/complete")
    public ResponseEntity<Post> complete(Post post) {
        Optional<Post> postData = postService.getPostDetails(post.getId());
        if (postData.isPresent()) {
            Post updatedPost = postService.completePost(postData.get(), post.getGiven());
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
