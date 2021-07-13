package com.vls.admin_postmanagementservice.service;

import com.vls.admin_postmanagementservice.model.Post;
import com.vls.admin_postmanagementservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPost(UUID postId) {
        return java.util.Optional.of(postRepository.findPostById(postId));
    }

    public void deletePost(UUID postId) {
        Post post = postRepository.findPostById(postId);
        post.setDeletion(true);
        postRepository.save(post);
    }

    public boolean checkIfDeletePossible(Post post) {
        if(post.getStatus().equalsIgnoreCase("Mở")) {
            return true;
        }
        return false;
    }
}
