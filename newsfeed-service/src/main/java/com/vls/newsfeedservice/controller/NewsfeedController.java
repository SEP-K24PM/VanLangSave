package com.vls.newsfeedservice.controller;

import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.service.NewsfeedService;
import com.vls.newsfeedservice.service.PostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

public class NewsfeedController {

    private final NewsfeedService newsfeedService;
    private final PostDetailService postDetailService;

    @Autowired
    public NewsfeedController(NewsfeedService newsfeedService, PostDetailService postDetailService) {
        this.newsfeedService = newsfeedService;

        this.postDetailService = postDetailService;
    }


    @RequestMapping("/")
    public ResponseEntity<List<Post>> newsFeed(){
        List<Post> list = newsfeedService.getAllPost();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping("/detail/{id}")
    public ResponseEntity<Thing> postDetail (@PathVariable("id") UUID id) {
        Optional<Thing> postDetails = postDetailService.getPostDetail(id);
        return postDetails.map(thing -> new ResponseEntity<>(thing, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}