package com.vls.adminreportmanagementservice.controller;

import com.vls.adminreportmanagementservice.model.Post_Report;
import com.vls.adminreportmanagementservice.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Autowired
    public AdminReportController(AdminReportService adminReportService) {
        this.adminReportService = adminReportService;

    }

    @RequestMapping("/list")
    public ResponseEntity<List<Post_Report>> getAllReports() {
        List<Post_Report> post_reports = adminReportService.getReports();
        return new ResponseEntity<>(post_reports, HttpStatus.OK);
    }

    @RequestMapping("/details/{id}")
    public ResponseEntity<Post_Report> getReportDetail (@PathVariable("id") UUID id) {
        Optional<Post_Report> reportDetail = adminReportService.findPost_reportById(id);
        if (reportDetail.isPresent()) {
            return new ResponseEntity<>(reportDetail.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
