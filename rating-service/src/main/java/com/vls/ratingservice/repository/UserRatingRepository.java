package com.vls.ratingservice.repository;

import com.vls.ratingservice.model.User_Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRatingRepository extends JpaRepository<User_Rating, UUID> {
    public List<User_Rating> findAllByRatedUserIdEquals(UUID userId);
}
