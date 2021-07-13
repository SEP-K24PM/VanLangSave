package com.vls.admin_postmanagementservice.controller;

import com.vls.admin_postmanagementservice.model.Post;
import com.vls.admin_postmanagementservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class HidePostController {
    private final PostService postService;

    @Autowired
    public HidePostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public ResponseEntity<Boolean> hide(@RequestBody UUID id){
        Optional<Post> postData = postService.getPost(id);
        if(postData.isPresent()) {
            Post post = postData.get();
            if(postService.checkIfHidePossible(post)) {
                postService.hidePost(post.getId());
                return new ResponseEntity<>(false, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
