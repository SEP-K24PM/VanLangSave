package com.vls.tradeservice.repository;

import com.vls.tradeservice.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThingRepo extends JpaRepository<Thing, UUID> {
//    public List<Thing> findByUser_id(UUID userId);
//    public List<Thing> findByUserid(UUID uuid);
}
