package com.vls.postservice.controller;

import com.vls.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Object> post() {
        List<Object> list = restTemplate.getForObject("http://post-save-service/posts/", List.class);
        return list;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Post> post(@RequestBody Post post) {
        Post result = restTemplate.postForObject("http://post-save-service/post/", post, Post.class);
//        HttpEntity<Post> request = new HttpEntity<>(post);
//        ResponseEntity<Post> resultttt = restTemplate.exchange("http://post-save-service/post/", HttpMethod.POST, request, Post.class);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
//        return resultttt;
    }

}
