package com.vls.postsaveservice.repository;

import com.vls.postsaveservice.model.post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<post, UUID> {

}
