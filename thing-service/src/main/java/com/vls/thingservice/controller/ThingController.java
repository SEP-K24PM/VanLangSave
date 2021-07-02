package com.vls.thingservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThingController {
    @RequestMapping("/list")
    public String getAllThings() {
        return "allThings";
    }
}
