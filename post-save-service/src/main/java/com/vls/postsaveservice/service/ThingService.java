package com.vls.postsaveservice.service;

import com.vls.postsaveservice.model.Thing;
import com.vls.postsaveservice.repository.ThingRepository;
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

    public Thing updateThingWithNewPost(UUID thingId, UUID postId) {
        Thing thing = findThingById(thingId);
        thing.setPost_id(postId);
        return thingRepository.save(thing);
    }
}
