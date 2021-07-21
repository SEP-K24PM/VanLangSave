package com.vls.thingservice.controller;

import com.vls.thingservice.model.Category;
import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.model.ThingForSaving;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.PostService;
import com.vls.thingservice.service.ThingService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DTO.ThingDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ThingController {
    private final ThingService thingService;
    private final ModelMapper modelMapper;
    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    public ThingController(ThingService thingService, ModelMapper modelMapper, PostService postService, CategoryService categoryService) {
        this.thingService = thingService;
        this.modelMapper = modelMapper;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/list/{userId}")
    public ResponseEntity<List<Thing>> getAllThings(@PathVariable("userId") String userId) {
        List<Thing> things = thingService.getListThings(UUID.fromString(userId));
        return new ResponseEntity<>(things, HttpStatus.OK);
    }

    @RequestMapping(value = "/details/{thingId}")
    public ResponseEntity<ThingDTO> getThingDetails(@PathVariable("thingId") String thingId) {
        Optional<Thing> thing = thingService.getThingDetails(thingId);
        if(thing.isPresent()) {
            ThingDTO thingDTO = modelMapper.map(thing.get(), ThingDTO.class);
            return new ResponseEntity<>(thingDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ThingForSaving> addThing(@RequestBody ThingDTO thingDTO) {
        ThingForSaving thing = modelMapper.map(thingDTO, ThingForSaving.class);
        ThingForSaving _thing = thingService.addThing(thing);
        return new ResponseEntity<>(_thing, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{thingId}", method = RequestMethod.POST)
    public ResponseEntity<ThingForSaving> updateThing(@PathVariable String thingId, @RequestBody ThingDTO thingDTO) {
        Optional<ThingForSaving> thingData = thingService.getThingFSDetails(thingId);
        if(thingData.isPresent()) {
            if(postService.findPostByThingId(thingData.get().getId()) == null) {
                ThingForSaving _thing = thingData.get();
                _thing.setId(UUID.fromString(thingId));
                _thing.setThing_name(thingDTO.getThing_name());
                _thing.setOrigin(thingDTO.getOrigin());
                _thing.setPrice(thingDTO.getPrice());
                _thing.setQuantity(thingDTO.getQuantity());
                _thing.setImage(thingDTO.getImage());
                _thing.setUser_id(thingDTO.getUser_id());
                _thing.setUsed_time(thingDTO.getUsed_time());
                return new ResponseEntity<>(thingService.updateThing(_thing), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("delete/{thingId}")
    public ResponseEntity<Thing> deleteThing(@PathVariable("thingId") String thingId) {
        Optional<ThingForSaving> thing = thingService.getThingFSDetails(thingId);
        if(thing.isPresent()) {
            if(postService.findPostByThingId(thing.get().getId()) == null) {
                thingService.deleteThing(thingId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/list-available/{userId}")
    public ResponseEntity<List<Thing>> getListAvaialbe(@PathVariable("userId") String userId) {
        List<Thing> things = thingService.getListThingsAvailable(UUID.fromString(userId));
        return new ResponseEntity<>(things, HttpStatus.OK);
    }

    @RequestMapping("/get-post/{thingId}")
    public ResponseEntity<Post> getPostByThingId(@PathVariable("thingId") String thingId) {
        Post post = postService.findPostByThingId(UUID.fromString(thingId));
        if(post != null) {
            if(post.isDeletion() == false && post.isVisible() == true) {
                return new ResponseEntity<Post>(post, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/get-categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> list = categoryService.getCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
