package com.vls.accountservice.repository;

import com.vls.accountservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    public Post findByThingIdEquals(UUID thing_id);
}
