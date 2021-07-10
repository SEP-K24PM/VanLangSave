package com.vls.newsfeedservice.controller;

import com.vls.newsfeedservice.dto.PostWithThing;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.service.PostService;
import com.vls.newsfeedservice.service.ThingService;
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

    private final PostService postService;

    @Autowired
    public NewsfeedController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public ResponseEntity<List<Post>> newsFeed(){
        List<Post> list = postService.getAllPost();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping("/post/{id}")
    public ResponseEntity<PostWithThing> postDetails (@PathVariable("id") UUID id){
        Optional<Post> post = postService.getPost(id);
        if(post.isPresent()) {
            PostWithThing postDetails = postService.getPostDetailsWithThing(post.get());
            return new ResponseEntity<>(postDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}