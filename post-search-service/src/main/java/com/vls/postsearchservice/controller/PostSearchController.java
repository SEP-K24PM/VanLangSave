package com.vls.postsearchservice.controller;

import com.vls.postsearchservice.model.Post;
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
    public String posts(@RequestBody String name) {
        List<Post> posts = new ArrayList<Post>();
        postRepository.findByThingName(name).forEach(posts::add);
        return name;
    }
}
