package com.vls.thingservice.service;

import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
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
    private final CategoryService categoryService;

    @Autowired
    public ThingService(ThingRepository thingRepository, PostService postService, CategoryService categoryService) {
        this.thingRepository = thingRepository;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    public List<Thing> getListThings(UUID userId) {
        List<Thing> list = thingRepository.findByUserid(userId);
        return categoryService.addCategoryNameToThing(list);
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

    public boolean checkIsPossibleToUpdateOrDelete(Thing thing) {
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

    public void deleteThing(String thingId) {
        thingRepository.deleteById(UUID.fromString(thingId));
    }

    public List<Thing> getListThingsAvailable(UUID userId) {
        List<Thing> list = thingRepository.findAvailableThing(userId);
        return categoryService.addCategoryNameToThing(list);
    }
}
