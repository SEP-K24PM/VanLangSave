package com.vls.thingservice.controller;

import com.vls.thingservice.model.Thing;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ThingController {

    private final ThingService thingService;
    private final CategoryService categoryService;

    @Autowired
    public ThingController(ThingService thingService, CategoryService categoryService) {
        this.thingService = thingService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/list/{userId}")
    public List<Thing> getAllThings(@PathVariable("userId") String userId) {
        UUID userID = UUID.fromString(userId);
        List<Thing> list = thingService.getListThings(userID);
        List<Thing> listWithCateName = categoryService.addCategoryNameToThing(list);
        return listWithCateName;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Thing addThing(@RequestBody Thing thing) {
        Thing savedThing = thingService.addThing(thing);
        return savedThing;
    }
}
