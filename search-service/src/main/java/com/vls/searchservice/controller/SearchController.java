package com.vls.searchservice.controller;

import com.vls.searchservice.dto.postelastic;
import com.vls.searchservice.model.Post;
import com.vls.searchservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class SearchController {

    private final RestTemplate restTemplate;
    private final PostService postService;

    @Autowired
    public SearchController(RestTemplate restTemplate, PostService postService) {
        this.restTemplate = restTemplate;
        this.postService = postService;
    }

    @RequestMapping("/posts")
    public ResponseEntity<List<postelastic>> posts(@RequestBody String search) throws ParseException {
//        List<LinkedHashMap> result = restTemplate.postForObject("http://post-search-service/posts", search, List.class);
//        List<postelastic> convertedResult = new ArrayList<>();
//        if(result.size() > 0)
//            convertedResult.addAll(postService.convertToPostElasticList(result));
        List<postelastic> convertedResult = restTemplate.postForObject("http://post-search-service/posts", search, List.class);
        return new ResponseEntity<>(convertedResult, HttpStatus.OK);
    }
}
