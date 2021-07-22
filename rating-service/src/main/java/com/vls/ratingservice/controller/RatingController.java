package com.vls.ratingservice.controller;

import com.vls.ratingservice.model.UserRating;
import com.vls.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<UserRating>> listRatings(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(ratingService.getRatingList(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<UserRating> createRating(@RequestBody UserRating userRating) {
        return new ResponseEntity<>(ratingService.createRating(userRating), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list-by-post/{postId}", method = RequestMethod.POST)
    public ResponseEntity<List<UserRating>> listRatingsByPost(@PathVariable("postId") UUID postId) {
        return new ResponseEntity<>(ratingService.getRatingsByPost(postId), HttpStatus.OK);
    }
}
