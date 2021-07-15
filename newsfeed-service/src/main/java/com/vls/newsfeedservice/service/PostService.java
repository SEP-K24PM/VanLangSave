package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.repository.PostRepository;
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

    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        postRepository.findAllNewPost("Mở").forEach(list::add);
        return list;
    }

    public Optional<Post> getPost(UUID postId) {
        return postRepository.findById(postId);
    }
}
