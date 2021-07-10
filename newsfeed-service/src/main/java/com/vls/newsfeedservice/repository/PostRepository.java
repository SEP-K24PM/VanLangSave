package com.vls.newsfeedservice.repository;

import com.vls.newsfeedservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p ORDER BY p.created_time DESC")
    List<Post> findAllNewPost();
}





