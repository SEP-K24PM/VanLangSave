package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.dto.PostWithThing;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.repository.PostRepository;
import com.vls.newsfeedservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        postRepository.findAllNewPost().forEach(list::add);
        return list;
    }

    public Optional<Post> getPost(UUID postId) {
        return postRepository.findById(postId);
    }

    public PostWithThing getPostDetailsWithThing(Post post) {
        Optional<Thing> thing = thingService.getThing(post.getThing_id());
        PostWithThing postDetails = new PostWithThing(post.getId(), post.getDescription(), post.getCreated_time(),
                post.getVisible(), post.getDeletion(),
             post.getContact(), post.getThing_id(), post.getExchange_method(),
                post.getStatus(), thing.get());
        return postDetails;
    }
}
