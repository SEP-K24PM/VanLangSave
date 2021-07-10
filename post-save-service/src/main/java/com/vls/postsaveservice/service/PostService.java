package com.vls.postsaveservice.service;

import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.model.Thing;
import com.vls.postsaveservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ThingService thingService;

    @Autowired
    public PostService(PostRepository postRepository, ThingService thingService) {
        this.postRepository = postRepository;
        this.thingService = thingService;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public boolean checkThingIsAvailable(UUID thingId) {
        Thing thing = thingService.findThingById(thingId);
        if(thing.getPost_id() == null)
            return true;
        return false;
    }
}
