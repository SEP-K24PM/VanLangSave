package com.vls.adminreportmanagementservice.service;

import com.vls.adminreportmanagementservice.model.Post_report;
import com.vls.adminreportmanagementservice.repository.AdminreportRepository;
import com.vls.adminreportmanagementservice.repository.PostreportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class  AdminReportService {

    private final AdminreportRepository adminreportRepository;
    private final PostreportRepository postreportRepository;

    @Autowired
    public AdminReportService(AdminreportRepository adminreportRepository, PostreportRepository postreportRepository) {
        this.adminreportRepository = adminreportRepository;
        this.postreportRepository = postreportRepository;
    }

    public List<Post_report> getReports() {
        List<Post_report> post_reports = new ArrayList<Post_report>();
        adminreportRepository.findAll().forEach(post_reports::add);
        return post_reports;
    }

    public Post_report findPost_reportById(UUID id) {
        return postreportRepository.findPost_reportById(id);
    }
}
