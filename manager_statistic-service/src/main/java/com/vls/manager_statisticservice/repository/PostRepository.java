package com.vls.manager_statisticservice.repository;

import com.vls.manager_statisticservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p WHERE extract (month FROM p.created_time) = extract (month FROM CURRENT_DATE) AND p.status = 'Hoàn tất'")
    List<Post> findByStatus(String status);
}
