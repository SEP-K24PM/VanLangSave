package com.vls.managementservice.controller;

import com.vls.managementservice.model.Category;
import com.vls.managementservice.model.Post;
import com.vls.managementservice.model.PostWT;
import com.vls.managementservice.service.CategoryService;
import com.vls.managementservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    public ManagerController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/post")
    public ResponseEntity<List<PostWT>> getSucessPost() {
        List<PostWT> posts = postService.findByStatus();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.countAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
