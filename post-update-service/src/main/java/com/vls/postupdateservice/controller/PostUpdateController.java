package com.vls.postupdateservice.controller;

import com.vls.postupdateservice.model.Post;
import com.vls.postupdateservice.service.PostElasticService;
import com.vls.postupdateservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PostUpdateController {

    private final PostService postService;
    private final PostElasticService postElasticService;

    @Autowired
    public PostUpdateController(PostService postService, PostElasticService postElasticService) {
        this.postService = postService;
        this.postElasticService = postElasticService;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Post> update(@RequestBody Post post) {
        Optional<Post> postData = postService.getPostDetails(post.getId());
        if(postData.isPresent()) {
            if(postService.checkIfAllowUpdate(postData.get())) {
                Post _post = postData.get();
                _post.setId(post.getId());
                _post.setDescription(post.getDescription());
                _post.setContact(post.getContact());
                _post.setExchange_method(post.getExchange_method());
                new Thread(() -> {
                    postElasticService.update(_post);
                }).start();
                return new ResponseEntity<>(postService.updatePost(_post), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
