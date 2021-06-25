package com.vls.saveservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveController {

    @RequestMapping("/")
    public String home() {
        return "hello";
    }
}
