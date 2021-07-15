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
        Post post = new Post();
        Date date = new Date();
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.setTime(date);
        int month = currentMonth.get(Calendar.MONTH);
        Calendar postMonth = Calendar.getInstance();
        postMonth.setTime(post.getCreated_time());
        int postTime = postMonth.get(Calendar.MONTH);

        if (month == postTime) {
            List<Post> posts = postService.findByStatus();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
