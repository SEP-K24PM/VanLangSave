package com.vls.thingservice.service;

import com.vls.thingservice.model.Thing;
import com.vls.thingservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThingService {
    private final ThingRepository thingRepository;

    @Autowired
    public ThingService(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    public List<Thing> getListThings(UUID userId) {
        return thingRepository.findByUserid(userId);
    }

    public Thing addThing(Thing thing) {
        Thing savedThing = thingRepository.save(thing);
        savedThing.setImage(savedThing.getId().toString()+".png");
        thingRepository.save(savedThing);
        return savedThing;
    }

}
