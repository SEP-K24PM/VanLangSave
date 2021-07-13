package com.vls.admin_postmanagementservice.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.vls.admin_postmanagementservice.model.Post;
import com.vls.admin_postmanagementservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class AdminDeleteController {

    private final PostService postService;

    @Autowired
    public AdminDeleteController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public ResponseEntity<Boolean> delete(@RequestBody UUID postId) {
        Optional<Post> postData = postService.getPost(postId);
        if(postData.isPresent()) {
            Post post = postData.get();
            if(postService.checkIfDeletePossible(post)) {
                postService.deletePost(post.getId());
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
