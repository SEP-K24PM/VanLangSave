package com.vls.thingservice.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.vls.thingservice.model.Thing;
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

    @Autowired
    public ThingController(ThingService thingService) {
        this.thingService = thingService;
    }

    @RequestMapping("/list/{userId}")
    public ResponseEntity<List<Thing>> getAllThings(@PathVariable("userId") String userId) {
        List<Thing> things = thingService.getListThings(UUID.fromString(userId));
        return new ResponseEntity<>(things, HttpStatus.OK);
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
        Thing _thing = thingService.addThing(thing);
        return new ResponseEntity<>(_thi ng, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{thingId}", method = RequestMethod.PUT)
    public ResponseEntity<Thing> updateThing(@PathVariable String thingId, @RequestBody Thing thing) {
        Optional<Thing> thingData = thingService.getThingDetails(thingId);
        if(thingData.isPresent()) {
            if(thingService.checkIsPosibleToUpdate(thingData.get())) {
                Thing _thing = thingData.get();
                _thing.setId(UUID.fromString(thingId));
                _thing.setThing_name(thing.getThing_name());
                _thing.setOrigin(thing.getOrigin());
                _thing.setPrice(thing.getPrice());
                _thing.setQuantity(thing.getQuantity());
                _thing.setImage(thing.getImage());
                _thing.setUserid(thing.getUserid());
                _thing.setUsed_time(thing.getUsed_time());
                _thing.setCategory_id(thing.getCategory_id());
                _thing.setPost_id(thing.getPost_id());
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
            if(thingService.checkIsPosibleToUpdate(thing.get())) {
                boolean result = thingService.deleteThing(thingId);
                if(result) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
