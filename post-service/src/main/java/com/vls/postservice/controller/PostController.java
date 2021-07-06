package com.vls.postservice.controller;

import com.vls.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

//    @RequestMapping(value = "/update/{postId}")
//    public ResponseEntity<Post> update(@PathVariable("postId") String postId) {
//
//    }

    @RequestMapping("/testupdate")
    public String testUpdate() {
        return restTemplate.getForObject("http://post-update-service/", String.class);
    }
}
