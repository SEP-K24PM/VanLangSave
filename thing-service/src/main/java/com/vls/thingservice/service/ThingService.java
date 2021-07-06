package com.vls.thingservice.service;

import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.repository.PostRepository;
import com.vls.thingservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThingService {
    private final ThingRepository thingRepository;
    private final PostService postService;

    @Autowired
    public ThingService(ThingRepository thingRepository, PostService postService) {
        this.thingRepository = thingRepository;
        this.postService = postService;
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

    public Thing updateThing(Thing thing) {
        return thingRepository.save(thing);
    }

    public boolean checkIsPosibleToUpdate(Thing thing) {
        if(thing.getPost_id() != null) {
            Optional<Post> post = postService.getPost(thing.getPost_id());
            if(post.isPresent()) {
                if(post.get().getStatus().equalsIgnoreCase("Mở")) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean deleteThing(String thingId) {
        try {
            thingRepository.deleteById(UUID.fromString(thingId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
