package com.vls.ratingservice.service;

import DTO.PostDTO;
import com.vls.ratingservice.model.UserRating;
import com.vls.ratingservice.repository.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService {
    private final UserRatingRepository userRatingRepository;

    @Autowired
    public RatingService(UserRatingRepository userRatingRepository) {
        this.userRatingRepository = userRatingRepository;
    }

    public List<UserRating> getRatingList(UUID userId) {
        return userRatingRepository.findAllByUser(userId);
    }

    public UserRating createRating(UserRating userRating) {
        return userRatingRepository.save(userRating);
    }

}
