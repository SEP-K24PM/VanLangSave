package com.vls.manager_statisticservice.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.vls.manager_statisticservice.model.Post;
import com.vls.manager_statisticservice.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
public class StatisticController {
    private final PostService postService;

    @Autowired
    public StatisticController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/post")
    public ResponseEntity<List<Post>> getSucessPost() {
        List<Post> posts = postService.findByStatusAndMonth();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    
    }


}
