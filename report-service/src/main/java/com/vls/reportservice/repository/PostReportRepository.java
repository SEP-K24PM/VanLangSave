package com.vls.reportservice.repository;


import com.vls.reportservice.model.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostReportRepository extends JpaRepository<PostReport, UUID> {
}
