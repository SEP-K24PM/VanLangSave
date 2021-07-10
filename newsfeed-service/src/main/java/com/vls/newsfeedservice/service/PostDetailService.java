package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostDetailService {
    public Optional<Thing> getPostDetail(UUID id) {
        return postDetailRepository.findById(id);
    }

    @Autowired
    private ThingRepository postDetailRepository;

}
