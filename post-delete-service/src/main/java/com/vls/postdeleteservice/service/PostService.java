package com.vls.postdeleteservice.service;

import com.vls.postdeleteservice.model.Post;
import com.vls.postdeleteservice.model.Thing;
import com.vls.postdeleteservice.repository.PostRepository;
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

    public Optional<Post> getPost(UUID postId) {
        return postRepository.findById(postId);
    }

    public boolean deletePost(Post post) {
        try {
            postRepository.delete(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkIfDeletePossible(Post post) {
        if(post.getStatus().equalsIgnoreCase("Mở")) {
            return true;
        }
        return false;
    }

}
