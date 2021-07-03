package com.vls.admin_reportmanagementservice.controller;

import com.vls.admin_reportmanagementservice.model.Post_report;
import com.vls.admin_reportmanagementservice.service.AdminReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminReportController {

    private final AdminReportService adminreportService;

    @Autowired
    public AdminReportController(AdminReportService adminreportService) {
        this.adminreportService = adminreportService;
    }

    @RequestMapping("/reports")
    public List<Post_report> getAllReports() {
        List<Post_report> list = adminreportService.getAllReports();
        return list;
    }


}
