package com.vls.postdeleteservice.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.vls.postdeleteservice.model.Post;
import com.vls.postdeleteservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PostDeleteController {

    private final PostService postService;
    private final ThingService thingService;

    @Autowired
    public PostDeleteController(PostService postService, ThingService thingService) {
        this.postService = postService;
        this.thingService = thingService;
    }

    @RequestMapping("/")
    public ResponseEntity<Boolean> delete(@RequestBody UUID postId) {
        Optional<Post> postData = postService.getPost(postId);
        if(postData.isPresent()) {
            if(postService.checkIfDeletePossible(postData.get())) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
