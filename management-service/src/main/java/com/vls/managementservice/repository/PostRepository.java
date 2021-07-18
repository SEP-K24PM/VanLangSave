package com.vls.managementservice.repository;

import java.util.Optional;
import java.util.UUID;

import com.vls.managementservice.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
    @Query("select p from Post p where p.thing_id = :thingId")
    public Post findByThingIdEquals(UUID thingId);
}
