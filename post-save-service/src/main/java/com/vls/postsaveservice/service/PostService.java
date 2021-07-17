package com.vls.postsaveservice.service;

import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.model.Thing;
import com.vls.postsaveservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Constants.ActionConstants;

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

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPostDetails(UUID id) {
        return postRepository.findById(id);
    }

    public boolean checkIfAllowUpdate(Post post) {
        if(post.getStatus().equalsIgnoreCase(ActionConstants.PostStatus.OPEN))
            return true;
        return false;
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public boolean checkIfDeletePossible(Post post) {
        if(post.getStatus().equalsIgnoreCase(ActionConstants.PostStatus.OPEN)) {
            return true;
        }
        return false;
    }
}
