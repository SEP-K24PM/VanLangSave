package com.vls.manager_statisticservice.service;

import com.vls.manager_statisticservice.model.Post;
import com.vls.manager_statisticservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PostService  {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findByStatus() {
        List<Post> list = new ArrayList<>();
        postRepository.findByStatus("Hoàn tất").forEach(list::add);
        return list;
    }
}
