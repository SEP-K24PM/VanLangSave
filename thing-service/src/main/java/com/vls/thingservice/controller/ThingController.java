package com.vls.thingservice.controller;

import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
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

    @Autowired
    public ThingController(ThingService thingService, ModelMapper modelMapper, PostService postService) {
        this.thingService = thingService;
        this.modelMapper = modelMapper;
        this.postService = postService;
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
    public ResponseEntity<Thing> addThing(@RequestBody Thing thing) {
        Thing _thing = thingService.addThing(thing);
        return new ResponseEntity<>(_thing, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{thingId}", method = RequestMethod.POST)
    public ResponseEntity<Thing> updateThing(@PathVariable String thingId, @RequestBody Thing thing) {
        Optional<Thing> thingData = thingService.getThingDetails(thingId);
        if(thingData.isPresent()) {
            if(thingService.checkIsPossibleToUpdateOrDelete(thingData.get())) {
                Thing _thing = thingData.get();
                _thing.setId(UUID.fromString(thingId));
                _thing.setThing_name(thing.getThing_name());
                _thing.setOrigin(thing.getOrigin());
                _thing.setPrice(thing.getPrice());
                _thing.setQuantity(thing.getQuantity());
                _thing.setImage(thing.getImage());
                _thing.setUserid(thing.getUserid());
                _thing.setUsed_time(thing.getUsed_time());

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
        Optional<Thing> thing = thingService.getThingDetails(thingId);
        if(thing.isPresent()) {
            if(thingService.checkIsPossibleToUpdateOrDelete(thing.get())) {
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
}
