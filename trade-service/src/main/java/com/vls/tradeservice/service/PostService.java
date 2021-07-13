package com.vls.tradeservice.service;

import com.vls.tradeservice.model.Post;
import com.vls.tradeservice.repository.PostRepository;
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

    public Post getPost(UUID postId) {
        return postRepository.findById(postId).get();
    }

    public void closePost(Post post) {
        post.setStatus("Đóng");
        postRepository.save(post);
    }
}