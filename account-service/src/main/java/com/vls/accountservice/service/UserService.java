package com.vls.accountservice.service;

import com.vls.accountservice.model.Account;
import com.vls.accountservice.model.Post;
import com.vls.accountservice.model.Thing;
import com.vls.accountservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final PostRepository postRepository;

    @Autowired
    public UserService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPost(UUID thingId) {
        return postRepository.findByThingIdEquals(thingId);
    }

    public List<Post> getListPostByUser(UUID userId, Account user) {
        List<Post> posts = new ArrayList<>();
        List<Thing> things = user.getThings();
        for (int i = 0; i < things.size(); i++) {
            posts.add(getPost(things.get(i).getId()));
        }
        return posts;
    }
}
