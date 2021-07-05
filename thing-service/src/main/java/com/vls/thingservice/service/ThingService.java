package com.vls.thingservice.service;

import com.vls.thingservice.model.Thing;
import com.vls.thingservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Thing> getThingDetails(String thingId) {
        return thingRepository.findById(UUID.fromString(thingId));
    }

    public boolean checkIfThingExists(UUID id) {
        return thingRepository.existsById(id);
    }

    public Thing updateThing(Thing thing) {
        return thingRepository.save(thing);
    }

}
