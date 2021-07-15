package com.vls.managerservice.controller;

import com.vls.managerservice.model.Post;
import com.vls.managerservice.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
public class ManagerController {

    private final RestTemplate restTemplate;

    @Autowired
    public ManagerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public ResponseEntity<Post> post(@RequestBody Post post) {
        Post result = restTemplate.getForObject("http://manager-statistic-service/post/",  Post.class, post);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<Category> category(@RequestBody Category category) {
        Category result = restTemplate.getForObject("http://manager-statistic-service/category/",  Category.class, category);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
