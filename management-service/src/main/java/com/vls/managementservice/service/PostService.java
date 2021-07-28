package com.vls.managementservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vls.managementservice.model.Post;
import com.vls.managementservice.model.PostWT;
import com.vls.managementservice.repository.PostRepository;
import com.vls.managementservice.repository.PostWTRepository;

import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostWTRepository postWTRepository;

    public PostService(PostRepository postRepository, PostWTRepository postWTRepository) {
        this.postRepository = postRepository;
        this.postWTRepository = postWTRepository;
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

    public List<PostWT> findByStatus() {
        List<PostWT> list = new ArrayList<>();
        postWTRepository.findByStatus("Hoàn tất").forEach(list::add);
        return list;
    }
}
