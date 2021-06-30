package com.vls.reportservice.controller;

import com.vls.reportservice.model.Post_report;
import com.vls.reportservice.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping("/reports")
    public List<Post_report> getAllReports() {
        List<Post_report> list = reportService.getAllReports();
        return list;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
}
