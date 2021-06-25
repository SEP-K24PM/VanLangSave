package com.vls.saveservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SaveController {


    private final RestTemplate restTemplate;

    @Autowired
    public SaveController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/post")
    public List<Object> post() {
        List<Object> list = restTemplate.getForObject("http://post-save-service/posts/", List.class);
        return list;
    }
}
