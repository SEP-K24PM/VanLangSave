package com.vls.adminreportmanagementservice.service;

import com.vls.adminreportmanagementservice.model.Post_report;
import com.vls.adminreportmanagementservice.repository.AdminreportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class  AdminReportService {

    private final AdminreportRepository adminreportRepository;

    @Autowired
    public AdminReportService(AdminreportRepository adminreportRepository) {
        this.adminreportRepository = adminreportRepository;
    }

    public List<Post_report> getAllReports() {
        List<Post_report> list = new ArrayList<Post_report>();
        adminreportRepository.findAll().forEach(list::add);
        return list;
    }

    public Post_report findPost_reportById(UUID id) {
        return adminreportRepository.findPost_reportById(id);
    }
}
