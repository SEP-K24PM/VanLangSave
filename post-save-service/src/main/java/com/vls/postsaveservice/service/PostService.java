package com.vls.postsaveservice.service;

import com.vls.postsaveservice.model.post;
import com.vls.postsaveservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<post> getAllPosts() {
        List<post> list = new ArrayList<post>();
        postRepository.findAll().forEach(list::add);
        return list;
    }
}
