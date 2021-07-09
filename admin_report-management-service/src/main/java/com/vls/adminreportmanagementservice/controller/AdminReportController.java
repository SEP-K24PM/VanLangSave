package com.vls.adminreportmanagementservice.controller;

import com.vls.adminreportmanagementservice.model.Post_report;
import com.vls.adminreportmanagementservice.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Autowired
    public AdminReportController(AdminReportService adminReportService) {
        this.adminReportService = adminReportService;

    }

    @RequestMapping("/list")
    public ResponseEntity<List<Post_report>> getAllReports() {
        try {
            List<Post_report> post_reports = new ArrayList<>();
            adminReportService.getReports();
            if (post_reports.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(post_reports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/detail/{id}")
    public ResponseEntity<Post_report> getReportDetail (@PathVariable("id") UUID id) {
        Optional<Post_report> reportDetail = Optional.ofNullable(adminReportService.findPost_reportById(id));
        if (reportDetail.isPresent()) {
            return new ResponseEntity<>(reportDetail.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
