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
    private final PostElasticService postElasticService;

    @Autowired
    public PostDeleteController(PostService postService, ThingService thingService, PostElasticService postElasticService) {
        this.postService = postService;
        this.thingService = thingService;
        this.postElasticService = postElasticService;
    }

    @RequestMapping("/")
    public ResponseEntity<Boolean> delete(@RequestBody UUID postId) {
        Optional<Post> postData = postService.getPost(postId);
        if(postData.isPresent()) {
            Post post = postData.get();
            if(postService.checkIfDeletePossible(post)) {
                boolean delete = thingService.removePostIdFromThing(post.getThing_id());
                if(delete) {
                    postService.deletePost(post);
                    new Thread(() -> {
                        postElasticService.delete(post);
                    }).start();
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
