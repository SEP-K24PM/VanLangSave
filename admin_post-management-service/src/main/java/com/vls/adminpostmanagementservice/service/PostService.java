package com.vls.adminpostmanagementservice.service;

import com.vls.adminpostmanagementservice.model.Post;
import com.vls.adminpostmanagementservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
