package com.vls.rateservice.controller;

import com.vls.rateservice.model.User_Rating;
import com.vls.rateservice.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RateController {
    private final UserRatingService userRatingService;

    @Autowired
    public RateController(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<User_Rating>> list(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userRatingService.getListRating(UUID.fromString(userId)), HttpStatus.OK);
    }

//    @RequestMapping("/rate")
//    public ResponseEntity<User_Rating> rate(@RequestBody User_Rating userRating) {
//
//        userRatingService.checkIfAllowRate(userRating);
//    }
}
