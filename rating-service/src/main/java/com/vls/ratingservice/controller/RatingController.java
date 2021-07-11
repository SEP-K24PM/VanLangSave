package com.vls.ratingservice.controller;

import com.vls.ratingservice.model.User_Rating;
import com.vls.ratingservice.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RatingController {
    private final UserRatingService userRatingService;

    @Autowired
    public RatingController(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<User_Rating>> list(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userRatingService.getListRating(UUID.fromString(userId)), HttpStatus.OK);
    }
}
