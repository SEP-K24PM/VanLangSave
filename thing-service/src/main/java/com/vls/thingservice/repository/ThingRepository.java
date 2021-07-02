package com.vls.thingservice.repository;

import com.vls.thingservice.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThingRepository extends JpaRepository<Thing, UUID> {
    public List<Thing> findByUser_id(UUID userId);
}
