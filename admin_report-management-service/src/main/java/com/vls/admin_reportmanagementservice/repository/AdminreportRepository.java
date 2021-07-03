package com.vls.admin_reportmanagementservice.repository;

import com.vls.admin_reportmanagementservice.model.Post_report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminReportRepository extends JpaRepository<Post_report, UUID> {
    public Post_report findPost_reportById(UUID id);
}
