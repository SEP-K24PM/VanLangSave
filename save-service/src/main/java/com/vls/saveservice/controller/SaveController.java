package com.vls.saveservice.controller;

import com.vls.saveservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class SaveController {

    private final RestTemplate restTemplate;

    @Autowired
    public SaveController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public List<Object> post() {
        List<Object> list = restTemplate.getForObject("http://post-save-service/posts/", List.class);
        return list;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Post post(@RequestBody Post post) {
        Post result = restTemplate.postForObject("http://post-save-service/post/", post, Post.class);
        return result;
    }

}
