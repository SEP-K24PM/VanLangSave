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

    public Optional<Post> getPost(UUID id){
        return java.util.Optional.of(postRepository.findPostById(id));
    }

    public void hidePost(UUID id){
        Post post = postRepository.findPostById(id);
        post.setVisible(false);
        postRepository.save(post);

    }
    public boolean checkIfHidePossible(Post post) {
        return post.getStatus().equalsIgnoreCase("Mở");
    }
}
