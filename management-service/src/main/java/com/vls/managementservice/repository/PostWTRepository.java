package com.vls.managementservice.repository;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.PostWT;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostWTRepository extends JpaRepository<PostWT, UUID> {
    @Query("SELECT p FROM PostWT p WHERE extract (month FROM p.created_time) = extract (month FROM CURRENT_DATE) AND p.status = 'Hoàn tất'")
    public List<PostWT> findByStatus(String status);
}
