package com.vls.postupdateservice.service;

import com.vls.postupdateservice.model.Post;
import com.vls.postupdateservice.repository.PostRepository;
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

    public Optional<Post> getPostDetails(UUID id) {
        return postRepository.findById(id);
    }

    public boolean checkIfAllowUpdate(Post post) {
        if(post.getStatus().equalsIgnoreCase("Mở"))
            return true;
        return false;
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }
}
