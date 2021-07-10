package com.vls.adminreportmanagementservice.repository;

import com.vls.adminreportmanagementservice.model.Post_Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostreportRepository extends JpaRepository<Post_Report, UUID> {
}