package com.vls.thingservice.controller;

import com.vls.thingservice.model.Thing;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class ThingController {

    private final ThingService thingService;
    private final CategoryService categoryService;

    @Autowired
    public ThingController(ThingService thingService, CategoryService categoryService) {
        this.thingService = thingService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/list")
    public String getAllThings(Model model) {
        UUID userID = UUID.fromString("c77bd9d9-9177-4bca-bf15-62a071e866e9");
        List<Thing> list = thingService.getListThings(userID);
        List<Thing> listWithCateName = categoryService.addCategoryNameToThing(list);
        model.addAttribute("list", listWithCateName);
        return "allThings";
    }
}
