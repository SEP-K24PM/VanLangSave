package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ThingService {
    private final ThingRepository thingRepository;

    @Autowired
    public ThingService(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    public Optional<Thing> getThing(UUID thingId) {
        return thingRepository.findById(thingId);
    }
}
