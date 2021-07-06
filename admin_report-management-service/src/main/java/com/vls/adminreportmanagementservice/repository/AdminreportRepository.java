package com.vls.adminreportmanagementservice.repository;

import com.vls.adminreportmanagementservice.model.Post_report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminreportRepository extends JpaRepository<Post_report, UUID> {
    Post_report findPost_reportById(UUID id);
}
