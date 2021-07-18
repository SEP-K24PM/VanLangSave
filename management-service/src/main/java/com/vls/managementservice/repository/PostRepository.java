package com.vls.managementservice.repository;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
    @Query("select p from Post p where p.thing_id = :thingId")
    public Post findByThingIdEquals(UUID thingId);

    @Query("SELECT p FROM Post p WHERE extract (month FROM p.created_time) = extract (month FROM CURRENT_DATE) AND p.status = 'Hoàn tất'")
    public List<Post> findByStatus(String status);
}