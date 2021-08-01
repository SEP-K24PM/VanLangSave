package com.vls.searchservice.controller;

import DTO.PostDTO;
import com.vls.searchservice.dto.postelastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import Constants.PostSearchApiConstants;

import java.util.ArrayList;
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        List<postelastic> result = restTemplate.postForObject(PostSearchApiConstants.Post.POST_SEARCH, search, List.class);
        List<postelastic> result = restTemplate.postForObject(PostSearchApiConstants.Post.POST_SEARCH, new HttpEntity<String>(search, headers), List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/newsfeed")
    public ResponseEntity<List<PostDTO>> newsfeed() {
        List<PostDTO> result = restTemplate.getForObject(PostSearchApiConstants.Post.NEWSFEED, List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/details/{id}")
    public ResponseEntity<PostDTO> postDetails(@PathVariable("id") String id) {
        PostDTO result = restTemplate.postForObject(PostSearchApiConstants.Post.POST_DETAILS, id, PostDTO.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
