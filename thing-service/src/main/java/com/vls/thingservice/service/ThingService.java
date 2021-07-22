package com.vls.thingservice.service;

import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.model.ThingForSaving;
import com.vls.thingservice.repository.ThingFSRepository;
import com.vls.thingservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.ThingDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThingService {
    private final ThingRepository thingRepository;
    private final ThingFSRepository thingFSRepository;

    @Autowired
    public ThingService(ThingRepository thingRepository, ThingFSRepository thingFSRepository) {
        this.thingRepository = thingRepository;
        this.thingFSRepository = thingFSRepository;
    }

    public List<Thing> getListThings(UUID userId) {
        return thingRepository.findByUserid(userId);
    }

    public ThingForSaving addThing(ThingForSaving thing) {
        ThingForSaving savedThing = thingFSRepository.save(thing);
        savedThing.setImage(savedThing.getId().toString() + ".png");
        thingFSRepository.save(savedThing);
        return savedThing;
    }

    public Optional<Thing> getThingDetails(String thingId) {
        return thingRepository.findById(UUID.fromString(thingId));
    }

    public Optional<ThingForSaving> getThingFSDetails(String thingId) {
        return thingFSRepository.findById(UUID.fromString(thingId));
    }

    public ThingForSaving updateThing(String thingId, ThingDTO thingDTO) {
        ThingForSaving _thing = thingFSRepository.getOne(UUID.fromString(thingId));
        _thing.setThing_name(thingDTO.getThing_name());
        _thing.setOrigin(thingDTO.getOrigin());
        _thing.setPrice(thingDTO.getPrice());
        _thing.setQuantity(thingDTO.getQuantity());
        _thing.setImage(thingDTO.getImage());
        _thing.setUser_id(thingDTO.getUser_id());
        _thing.setUsed_time(thingDTO.getUsed_time());
        _thing.setCategory_id(thingDTO.getCategory_id());
        return thingFSRepository.save(_thing);
    }

    public void deleteThing(String thingId) {
        thingFSRepository.deleteById(UUID.fromString(thingId));
    }

    public List<Thing> getListThingsAvailable(UUID userId) {
        return thingRepository.findAvailableThing(userId);
    }
}
