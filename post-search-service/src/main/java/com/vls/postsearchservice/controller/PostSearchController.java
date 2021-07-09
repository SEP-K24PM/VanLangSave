package com.vls.postsearchservice.controller;

import com.vls.postsearchservice.dto.postelastic;
import com.vls.postsearchservice.repository.PostRepository;
import com.vls.postsearchservice.repository.PostDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostSearchController {

    private final PostRepository postRepository;
    private final PostDAOImpl postDAOIml;

    @Autowired
    public PostSearchController(PostRepository postRepository, PostDAOImpl postDAOIml) {
        this.postRepository = postRepository;
        this.postDAOIml = postDAOIml;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity<List<postelastic>> posts(@RequestBody String search) {
        List<postelastic> posts = postDAOIml.search(search);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
