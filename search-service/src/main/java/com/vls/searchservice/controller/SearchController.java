package com.vls.searchservice.controller;

import com.vls.searchservice.model.Post;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SearchController {

    private final RestTemplate restTemplate;

    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/posts")
    public String posts(@RequestBody String name) {
        String result = restTemplate.postForObject("http://post-search-service/posts", name, String.class);
        return result;
    }
}
