package com.vls.postduplicationservice.service;

import com.vls.postduplicationservice.dto.postelastic;
import com.vls.postduplicationservice.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostElasticService {
    private final PostRepository postRepository;

    @Autowired
    public PostElasticService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(postelastic postelastic) {
        postRepository.delete(postelastic);
    }
}
