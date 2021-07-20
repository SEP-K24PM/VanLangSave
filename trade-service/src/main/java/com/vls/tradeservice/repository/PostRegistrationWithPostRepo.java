package com.vls.tradeservice.repository;

import java.util.List;
import java.util.UUID;

import com.vls.tradeservice.model.PostRegistrationWithPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRegistrationWithPostRepo extends JpaRepository<PostRegistrationWithPost, UUID> {
    @Query("SELECT ele from PostRegistrationWithPost as ele where ele.user_id = :userId")
    public List<PostRegistrationWithPost> getUserRegisList(UUID userId);
}
