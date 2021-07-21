package com.vls.thingservice.repository;

import java.util.UUID;

import com.vls.thingservice.model.ThingForSaving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingFSRepository extends JpaRepository<ThingForSaving, UUID> {
    
}
