package com.vls.postsearchservice.controller;

import com.vls.postsearchservice.dto.postelastic;
import com.vls.postsearchservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostSearchController {

    private final PostRepository postRepository;

    @Autowired
    public PostSearchController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public List<postelastic> posts(@RequestBody String name) {
        List<postelastic> posts = new ArrayList<>();
        postRepository.findBy(name).forEach(posts::add);
        return posts;
    }

}
