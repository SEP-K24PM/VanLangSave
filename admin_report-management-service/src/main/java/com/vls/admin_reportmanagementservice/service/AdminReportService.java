package com.vls.admin_reportmanagementservice.service;

import com.vls.admin_reportmanagementservice.model.Post_report;
import com.vls.admin_reportmanagementservice.repository.AdminreportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminReportService {

    private final AdminReportRepository adminreportRepository;

    @Autowired
    public AdminReportService(AdminReportRepository adminreportRepository) {
        this.adminreportRepository = adminreportRepository;
    }

    public List<Post_report> getAllReports() {
        List<Post_report> list = new ArrayList<Post_report>();
        reportRepository.findAll().forEach(list::add);
        return list;
    }

}
