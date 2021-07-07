package com.vls.postupdateservice.service;

import com.vls.postupdateservice.model.Thing;
import com.vls.postupdateservice.repository.ThingRepository;
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

    public Thing updateThingWithUpdateost(UUID thingId, UUID postId) {
        Thing thing = findThingById(thingId);
        thing.setPost_id(postId);
        return thingRepository.save(thing);
    }

    public boolean checkThingIsAvailable(UUID thingId) {
        Thing thing = findThingById(thingId);
        if(thing.getPost_id() == null)
            return true;
        return false;
    }
}
