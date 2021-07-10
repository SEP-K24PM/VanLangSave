package com.vls.newsfeedservice.repository;

import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ThingRepository extends JpaRepository<Thing, UUID> {
}
//public Thing ThingRepository(UUID id);
