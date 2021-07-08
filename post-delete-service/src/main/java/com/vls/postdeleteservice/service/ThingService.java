package com.vls.postdeleteservice.service;

import com.vls.postdeleteservice.model.Thing;
import com.vls.postdeleteservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ThingService {
    private final ThingRepository thingRepository;

    @Autowired
    public ThingService(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    public Thing findThingById(UUID id) {
        return thingRepository.findThingById(id);
    }

    public Thing removePostIdFromThing(UUID thingId) {
        Thing thing = findThingById(thingId);
        thing.setPost_id(null);
        return thingRepository.save(thing);
    }

    public boolean checkPostOfThingIsAvailable(UUID thingId) {
        Thing thing = findThingById(thingId);
        if(thing.getPost_id() == null)
            return true;
        return false;
    }
}
