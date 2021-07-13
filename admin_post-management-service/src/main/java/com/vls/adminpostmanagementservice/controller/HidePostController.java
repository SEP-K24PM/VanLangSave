package com.vls.adminpostmanagementservice.controller;

import DTO.PostDTO;
import com.vls.adminpostmanagementservice.model.Post;
import com.vls.adminpostmanagementservice.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class HidePostController {
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public HidePostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/hide", method = RequestMethod.POST)
    public ResponseEntity<PostDTO> hide(@RequestBody String id){
        Optional<Post> postData = postService.getPost(UUID.fromString(id));
        if(postData.isPresent()) {
            Post post = postService.hidePost(postData.get());
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
