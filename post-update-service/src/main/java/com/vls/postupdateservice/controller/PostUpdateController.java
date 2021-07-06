package com.vls.postupdateservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostUpdateController {

    @RequestMapping("/")
    public String home() {
        return "hello from post update service";
    }
}
