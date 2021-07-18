package com.vls.managementservice.repository;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.UserHandling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHandlingRepository extends JpaRepository<UserHandling, UUID> {
    @Query("select h from UserHandling h where h.user_id = :user_id")
    public List<UserHandling> findHandlingByUserId(UUID user_id);
}
