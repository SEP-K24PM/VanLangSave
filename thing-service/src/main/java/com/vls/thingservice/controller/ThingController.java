package com.vls.thingservice.controller;

import com.vls.thingservice.model.Thing;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @RequestMapping(value = "/details/{thingId}")
    public ResponseEntity<Thing> getThingDetails(@PathVariable("thingId") String thingId) {
        Optional<Thing> thing = thingService.getThingDetails(thingId);
        if(thing.isPresent()) {
            return new ResponseEntity<>(thing.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Thing> addThing(@RequestBody Thing thing) {
        try {
            Thing _thing = thingService.addThing(thing);
            return new ResponseEntity<>(_thing, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Thing updateThing(@RequestBody Thing thing) {
        Thing updatedThing = new Thing();
        if(thingService.checkIfThingExists(thing.getId())) {
            updatedThing = thingService.updateThing(thing);
        }
        return updatedThing;
    }
}
