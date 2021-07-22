package com.vls.postsaveservice.service;

import com.vls.postsaveservice.model.*;
import com.vls.postsaveservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import Constants.ActionConstants;
import DTO.CategoryDTO;
import DTO.PostDTO;
import DTO.ThingDTO;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public Post createPost(Post post) {
        post.setStatus("Mở");
        post.setCreated_time(new Date());
        post.setVisible(true);
        return postRepository.save(post);
    }

    public PostDTO convertToPostDTO(Post post, Thing thing, Category category) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        ThingDTO thingDTO = modelMapper.map(thing, ThingDTO.class);
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        thingDTO.setCategory(categoryDTO);
        postDTO.setThing(thingDTO);
        return postDTO;
    }

    public Optional<Post> getPostDetails(UUID id) {
        return postRepository.findById(id);
    }

    public boolean checkIfAllowUpdate(Post post) {
        if (post.getStatus().equalsIgnoreCase(ActionConstants.PostStatus.OPEN))
            return true;
        return false;
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public boolean checkIfDeletePossible(Post post) {
        if (post.getStatus().equalsIgnoreCase(ActionConstants.PostStatus.OPEN)) {
            return true;
        }
        return false;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Post cancelPost(Post post) {
        post.setStatus(ActionConstants.PostStatus.CANCELLED);
        return postRepository.save(post);
    }

    public Post completePost(Post post, UUID userId, Thing thing) {
        if (thing.getUser_id().toString().equalsIgnoreCase(userId.toString())) {
            if (post.getGiver() == null) {
                post.setGiver(userId);
                if (post.getGiven() != null) {
                    post.setStatus(ActionConstants.PostStatus.COMPLETE);
                }
            }
        } else {
            if (post.getGiven() == null) {
                post.setGiven(userId);
                if (post.getGiver() != null) {
                    post.setStatus(ActionConstants.PostStatus.COMPLETE);
                }
            }
        }
        return postRepository.save(post);
    }
}
