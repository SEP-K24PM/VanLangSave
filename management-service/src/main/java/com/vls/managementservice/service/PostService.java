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

    public Post deletePost(Post post) {
        post.setDeletion(true);
        return postRepository.save(post);
    }

    public Post hidePost(Post post){
        post.setVisible(!post.isVisible());
        return postRepository.save(post);
    }
}
