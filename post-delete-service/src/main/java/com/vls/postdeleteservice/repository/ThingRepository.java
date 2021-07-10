package com.vls.postdeleteservice.repository;

import com.vls.postdeleteservice.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThingRepository extends JpaRepository<Thing, UUID> {
    public Thing findThingById(UUID id);
}
