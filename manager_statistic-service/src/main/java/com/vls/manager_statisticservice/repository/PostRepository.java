package com.vls.manager_statisticservice.repository;

import com.vls.manager_statisticservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByStatusAndMonth(String status, int month);
}
