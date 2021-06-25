package com.vls.postsaveservice.controller;

import com.vls.postsaveservice.model.post;
import com.vls.postsaveservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<post> getAllPosts() {
        List<post> list = postService.getAllPosts();
        return list;
    }
}
