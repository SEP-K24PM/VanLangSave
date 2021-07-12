package com.vls.reportservice.controller;

import com.vls.reportservice.model.PostReport;
import com.vls.reportservice.service.PostReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    private final PostReportService postReportService;

    @Autowired
    public ReportController(PostReportService postReportService) {
        this.postReportService = postReportService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<PostReport> create(@RequestBody PostReport postReport) {
        return new ResponseEntity<>(postReportService.createReport(postReport), HttpStatus.CREATED);
    }
}
