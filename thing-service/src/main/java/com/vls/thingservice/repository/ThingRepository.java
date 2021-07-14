package com.vls.thingservice.repository;

import com.vls.thingservice.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThingRepository extends JpaRepository<Thing, UUID> {
    public List<Thing> findByUserid(UUID uuid);

    @Query("SELECT t from Thing t left outer join Post p on p.thing_id = t.id where p.thing_id is null and t.userid = :thingId")
    public List<Thing> findAvailableThing(UUID thingId);
}
