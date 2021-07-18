package com.vls.managementservice.repository;

import java.util.UUID;

import com.vls.managementservice.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
    
}
