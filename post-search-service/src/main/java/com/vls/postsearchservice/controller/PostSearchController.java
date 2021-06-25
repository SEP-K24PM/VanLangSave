package com.vls.postsearchservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostSearchController {

    @RequestMapping("/posts")
    public String posts() {
        return "hello from post search";
    }
}
