package com.vls.searchservice.controller;

import com.vls.searchservice.dto.postelastic;
import com.vls.searchservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class SearchController {

    private final RestTemplate restTemplate;
    private final PostService postService;

    @Autowired
    public SearchController(RestTemplate restTemplate, PostService postService) {
        this.restTemplate = restTemplate;
        this.postService = postService;
    }

    @GetMapping(value = "/posts")
    public String posts(Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        model.addAttribute("baseUrl", url);
        return "market";
    }

    @RequestMapping("/posts")
    public String posts(@RequestBody String search, Model model) throws ParseException {
        if(!search.isBlank()) {
            List<LinkedHashMap> result = restTemplate.postForObject("http://post-search-service/posts", search, List.class);
            List<postelastic> convertedResult = new ArrayList<>();
            if(result.size() > 0)
                convertedResult.addAll(postService.convertToPostElasticList(result));
            model.addAttribute("result", convertedResult);
            model.addAttribute("resultCountMessage", "Có " + convertedResult.size() + " kết quả");
            model.addAttribute("blankInput", false);
        } else {
            model.addAttribute("blankInput", true);
        }
        return "result";
    }
}
