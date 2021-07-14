package com.vls.adminreportmanagementservice.controller;

import Constants.AdminApiConstants;
import DTO.PostDTO;
import DTO.PostReportDTO;
import com.vls.adminreportmanagementservice.model.Post_Report;
import com.vls.adminreportmanagementservice.service.*;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AdminReportController {
    private final AdminReportService adminReportService;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminReportController(AdminReportService adminReportService, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.adminReportService = adminReportService;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/list")
    public ResponseEntity<List<Post_Report>> getAllReports() {
        List<Post_Report> post_reports = adminReportService.getReports();
        return new ResponseEntity<>(post_reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResponseEntity<Post_Report> getReportDetail (@RequestBody String reportId) {
        Optional<Post_Report> reportDetail = adminReportService.findPostReportById(UUID.fromString(reportId));
        if (reportDetail.isPresent()) {
            return new ResponseEntity<>(reportDetail.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public ResponseEntity<PostReportDTO> handlingReport(@RequestBody PostReportDTO postReportDTO) {
        PostReportDTO result = new PostReportDTO();
        Post_Report postReport = modelMapper.map(postReportDTO, Post_Report.class);
        if(postReportDTO.getHandling().equalsIgnoreCase("Xoá bài đăng")){
            restTemplate.postForObject(AdminApiConstants.PostManage.SOFT_DELETE_POST,
                    postReportDTO.getPost().getId(), PostDTO.class);
            Post_Report updatedPostReport = adminReportService.updatePostReport(postReport);
            result = modelMapper.map(updatedPostReport, PostReportDTO.class);
        } else {
            restTemplate.postForObject(AdminApiConstants.PostManage.HIDE_POST,
                    postReportDTO.getPost().getId(), PostDTO.class);
            Post_Report updatedPostReport = adminReportService.updatePostReport(postReport);
            result = modelMapper.map(updatedPostReport, PostReportDTO.class);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
