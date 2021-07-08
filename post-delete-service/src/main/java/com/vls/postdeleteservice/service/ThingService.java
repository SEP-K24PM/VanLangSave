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

    public boolean removePostIdFromThing(UUID thingId) {
        try {
            Thing thing = findThingById(thingId);
            thing.setPost_id(null);
            thingRepository.save(thing);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
