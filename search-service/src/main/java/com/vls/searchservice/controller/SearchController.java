package com.vls.searchservice.controller;

import com.vls.searchservice.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class SearchController {

    private final RestTemplate restTemplate;

    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/posts")
    public String posts() {
        return "market";
    }

    @RequestMapping("/posts")
    public List<Post> posts(@RequestBody String name) {
        List<Post> result = restTemplate.postForObject("http://post-search-service/posts", name, List.class);
        return result;
    }
}
