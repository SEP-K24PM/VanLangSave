package com.vls.searchservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @RequestMapping("/")
    public String home() {
        return "hello search";
    }
}
