package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsfeedService {

    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        newsfeedRepository.findAllNewPost().forEach(list::add);
        return list;
    }


    @Autowired
    private PostRepository newsfeedRepository;

}
