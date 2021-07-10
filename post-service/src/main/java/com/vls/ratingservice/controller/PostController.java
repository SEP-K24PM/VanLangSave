package com.vls.ratingservice.controller;

import com.vls.ratingservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class PostController {

    private final RestTemplate restTemplate;

    @Autowired
    public PostController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Post> post(@RequestBody Post post) {
        Post result = restTemplate.postForObject("http://post-save-service/post/", post, Post.class);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{postId}")
    public ResponseEntity<Post> update(@PathVariable("postId") String postId, @RequestBody Post post) {
        post.setId(UUID.fromString(postId));
        Post result = restTemplate.postForObject("http://post-update-service/post/", post, Post.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{postId}")
    public ResponseEntity<Boolean> delete(@PathVariable("postId") UUID postId) {
        boolean result = restTemplate.postForObject("http://post-delete-service/", postId, Boolean.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
