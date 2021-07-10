package com.vls.ratingservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @RequestMapping("/")
    public String home() {
        return "hello from rating service";
    }
}
