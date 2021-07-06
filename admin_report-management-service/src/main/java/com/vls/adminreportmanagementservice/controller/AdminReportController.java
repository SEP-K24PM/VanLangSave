package com.vls.adminreportmanagementservice.controller;

import com.vls.adminreportmanagementservice.model.Post_report;
import com.vls.adminreportmanagementservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Autowired
    public AdminReportController(AdminReportService adminReportService) {
        this.adminReportService = adminReportService;

    }
    @RequestMapping("/")
    public String index() {return "index";}

    @RequestMapping("/reports")
    public List<Post_report> getAllReports() {
        List<Post_report> list = adminReportService.getAllReports();
        return list;
    }

    @RequestMapping("/reports/{id}")
    public Post_report getReportDetail (@PathVariable("id") UUID id) {
        return adminReportService.findPost_reportById(id);
    }

}
