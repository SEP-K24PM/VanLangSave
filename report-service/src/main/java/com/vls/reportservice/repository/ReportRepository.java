package com.vls.reportservice.repository;

import com.vls.reportservice.model.Post_report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Post_report, UUID> {
    public Post_report findPost_reportById(UUID id);
}
