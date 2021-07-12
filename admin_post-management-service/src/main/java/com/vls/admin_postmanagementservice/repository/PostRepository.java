package com.vls.admin_postmanagementservice.repository;

import com.vls.admin_postmanagementservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    public Post findPostById(UUID id);
}
