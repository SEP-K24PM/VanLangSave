package com.vls.adminreportmanagementservice.controller;

import com.vls.adminreportmanagementservice.model.Post_report;
import com.vls.adminreportmanagementservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminReportController{

private final AdminReportService adminReportService;


@Autowired
public AdminReportController(AdminReportService adminReportService){
        this.adminReportService = adminReportService;

        }

@RequestMapping("/reports")
public List<Post_report> getAllReports(){
        List<Post_report>  list = adminReportService.getAllReports();
        return list;
        }

        }
