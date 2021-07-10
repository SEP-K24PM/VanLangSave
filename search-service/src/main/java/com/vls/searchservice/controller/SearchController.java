package com.vls.searchservice.controller;

import com.vls.searchservice.dto.postelastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class SearchController {

    private final RestTemplate restTemplate;

    @Autowired
    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/posts")
    public ResponseEntity<List<postelastic>> posts(@RequestBody String search) {
        List<postelastic> result = restTemplate.postForObject("http://post-search-service/posts", search, List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
