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
        return thingRepository.findByUser_id(userId);
    }
}
