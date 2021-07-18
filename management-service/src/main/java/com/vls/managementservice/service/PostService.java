package com.vls.managementservice.service;

import java.util.Optional;
import java.util.UUID;

import com.vls.managementservice.model.Post;
import com.vls.managementservice.repository.PostRepository;

import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPost(UUID id){
        return postRepository.findById(id);
    }

    public void deletePost(Post post) {
        post.setDeletion(true);
        postRepository.save(post);
    }

    public void hidePost(Post post){
        post.setVisible(!post.isVisible());
        postRepository.save(post);
    }
}