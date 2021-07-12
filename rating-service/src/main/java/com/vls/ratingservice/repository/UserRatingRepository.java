package com.vls.ratingservice.repository;

import com.vls.ratingservice.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, UUID> {
    @Query("SELECT u FROM UserRating u WHERE u.rated_user_id = :userId")
    public List<UserRating> findAllByUser(@Param("userId") UUID userId);
}
