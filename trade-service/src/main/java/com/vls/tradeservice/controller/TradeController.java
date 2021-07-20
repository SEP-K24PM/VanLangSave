package com.vls.tradeservice.controller;

import com.vls.tradeservice.model.*;
import com.vls.tradeservice.service.PostRegistrationService;
import com.vls.tradeservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TradeController {
    private final PostRegistrationService postRegistrationService;
    private final PostService postService;

    @Autowired
    public TradeController(PostRegistrationService postRegistrationService, PostService postService) {
        this.postRegistrationService = postRegistrationService;
        this.postService = postService;
    }
    //duyệt đăng ký
    @RequestMapping(value = "/accept-register/{postRegisId}", method = RequestMethod.POST)
    public ResponseEntity<PostRegistration> chooseRegister(@PathVariable("postRegisId") UUID postRegisId) {
        Optional<PostRegistration> postRegistration = postRegistrationService.getPostRegis(postRegisId);
        if(postRegistration.isPresent()) {
            PostRegistration postRegistrationData = postRegistration.get();
            Post post = postService.getPost(postRegistrationData.getPost_id());
            if(post.getStatus().equalsIgnoreCase("Mở")) {
                postRegistrationService.setChosen(postRegistrationData);
                postService.closePost(post);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // gửi yêu cầu đổi đồ
    @RequestMapping(value = "/register-post", method = RequestMethod.POST)
    public ResponseEntity<PostRegistration> SavePostRegistration(@RequestBody PostRegistration postRegistration){
        var savedPostRegis = postRegistrationService.register(postRegistration);
        return new ResponseEntity<>(savedPostRegis, HttpStatus.CREATED);
    }

    //show toàn bộ yêu cầu đổi đồ của 1 bài post
    @RequestMapping(value = "/list-regis/{postID}",method = RequestMethod.GET)
    public ResponseEntity<List<PostRegistWithEntities>> loadListRegister(@PathVariable("postID") UUID postID) {
        List<PostRegistWithEntities> listRegister = postRegistrationService.getListPostRegis(postID);
        return new ResponseEntity<>(listRegister, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-regis/{userId}")
    public ResponseEntity<List<PostRegistrationWithPost>> userRegistList(@PathVariable("userId") UUID userId) {
        List<PostRegistrationWithPost> list = postRegistrationService.getUserRegisList(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}