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


}
