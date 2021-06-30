package com.vls.searchservice.controller;

import com.vls.searchservice.model.PostElastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {

    private final RestTemplate restTemplate;

    @Autowired
    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/posts")
    public String posts(Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        model.addAttribute("baseUrl", url);
        return "market";
    }

    @RequestMapping("/posts")
    public String posts(@RequestBody String search, Model model) {
        List<PostElastic> result = restTemplate.postForObject("http://post-search-service/posts", search, List.class);
        model.addAttribute("result", result);
        return "result";
    }
}
