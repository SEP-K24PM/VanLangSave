package com.vls.postsaveservice.controller;

import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostSaveController {

    private final PostService postService;

    @Autowired
    public PostSaveController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/posts")
    public List<Post> getAllPosts() {
        List<Post> list = postService.getAllPosts();
        return list;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            postService.createPost(post);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
