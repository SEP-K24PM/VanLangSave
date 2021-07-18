package com.vls.managementservice.repository;

import java.util.UUID;
import com.vls.managementservice.model.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends JpaRepository<PostReport, UUID> {
    
}
