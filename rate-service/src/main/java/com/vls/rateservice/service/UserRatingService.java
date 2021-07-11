package com.vls.rateservice.service;

import com.vls.rateservice.model.User_Rating;
import com.vls.rateservice.repository.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRatingService {
    private final UserRatingRepository userRatingRepository;

    @Autowired
    public UserRatingService(UserRatingRepository userRatingRepository) {
        this.userRatingRepository = userRatingRepository;
    }

    public List<User_Rating> getListRating(UUID userId) {
        List<User_Rating> list = userRatingRepository.findAllByRatedUserIdEquals(userId);
        return list;
    }

//
//    public boolean checkIfAllowRate(User_Rating userRating) {
//
//    }
}
