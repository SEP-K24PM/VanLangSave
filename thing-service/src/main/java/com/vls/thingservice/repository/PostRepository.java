package com.vls.thingservice.repository;

import com.vls.thingservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("select p from Post p where p.thing_id = :thingId")
    public Post findByThingId(UUID thingId);
}
