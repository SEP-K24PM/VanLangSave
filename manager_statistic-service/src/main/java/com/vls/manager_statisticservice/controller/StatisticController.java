package com.vls.manager_statisticservice.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.vls.manager_statisticservice.model.Post;

import com.vls.manager_statisticservice.model.Category;
import com.vls.manager_statisticservice.service.PostService;
import com.vls.manager_statisticservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class StatisticController {
    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    public StatisticController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/post")
    public ResponseEntity<List<Post>> getSucessPost() {
        List<Post> posts = postService.findByStatus();
        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    @RequestMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.countAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

}
