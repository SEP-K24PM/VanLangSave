package com.vls.postservice.controller;

import UrlService.UrlPost;
import com.vls.postservice.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PostController {
    private final RestTemplate restTemplate;
    private UrlPost urlPost;

    public PostController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Post> post(@RequestBody Post post) {
        Post result = restTemplate.postForObject(urlPost.postSave, post, Post.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}