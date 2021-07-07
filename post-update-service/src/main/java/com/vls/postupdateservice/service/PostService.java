package com.vls.postupdateservice.service;

import com.vls.postupdateservice.model.Post;
import com.vls.postupdateservice.model.Thing;
import com.vls.postupdateservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ThingService thingService;

    @Autowired
    public PostService(PostRepository postRepository, ThingService thingService) {
        this.postRepository = postRepository;
        this.thingService = thingService;
    }

    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<Post>();
        postRepository.findAll().forEach(list::add);
        return list;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
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
