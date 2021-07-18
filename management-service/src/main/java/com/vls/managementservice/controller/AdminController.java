package com.vls.managementservice.controller;

import Constants.ActionConstants;
import DTO.PostReportDTO;
import DTO.UserAccountDTO;
import com.vls.managementservice.model.AdminAccount;
import com.vls.managementservice.model.Post;
import com.vls.managementservice.model.PostReport;
import com.vls.managementservice.service.AdminService;
import com.vls.managementservice.service.PostReportService;
import com.vls.managementservice.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/admin")
@RestController
public class AdminController {
    private final AdminService adminService;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final PostReportService postReportService;
    private final PostService postService;

    @Autowired
    public AdminController(AdminService adminService, RestTemplate restTemplate, 
        ModelMapper modelMapper, PostReportService postReportService, PostService postService) {
        this.adminService = adminService;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.postReportService = postReportService;
        this.postService = postService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminAccount> login(@RequestBody AdminAccount adminAccount) {
        Optional<AdminAccount> admin = adminService.getAccount(adminAccount.getEmail());
        if(admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/list-report")
    public ResponseEntity<List<PostReportDTO>> hidePost() {
        List<PostReport> list = postReportService.getReports();
        List<PostReportDTO> result = new ArrayList<>();
        for (PostReport postReport : list) {
            result.add(modelMapper.map(postReport, PostReportDTO.class));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/details-report/{reportId}")
    public ResponseEntity<PostReportDTO> details(@PathVariable("reportId") String reportId) {
        Optional<PostReport> reportDetail = postReportService.findPostReportById(UUID.fromString(reportId));
        if (reportDetail.isPresent()) {
            PostReportDTO postReportDTO = modelMapper.map(reportDetail.get(), PostReportDTO.class);
            return new ResponseEntity<>(postReportDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/handle-report", method = RequestMethod.POST)
    public ResponseEntity<PostReportDTO> hidePost(@RequestBody PostReportDTO postReportDTO) {
        PostReport postReport = modelMapper.map(postReportDTO, PostReport.class);         
        Optional<Post> post = postService.getPost(postReport.getPost().getId());
        if(postReportDTO.getHandling().equalsIgnoreCase(ActionConstants.PostHandling.DELETE_POST)) {
            postService.deletePost(post.get());
            postReportService.updatePostReport(postReport);
        } else {
            postService.hidePost(post.get());
            postReportService.updatePostReport(postReport);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @RequestMapping(value = "/list-user")
    // public ResponseEntity<List<UserAccountDTO>> listUser() {
    //     List<UserAccountDTO> result = restTemplate.getForObject(AdminApiConstants.UserManage.LIST_USER, List.class);
    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }

    // @RequestMapping(value = "/block-user")
    // public ResponseEntity<UserAccountDTO> blockUser(@RequestBody UserAccountDTO userAccountDTO) {
    //     UserAccountDTO result = restTemplate.postForObject(AdminApiConstants.UserManage.BLOCK_USER, userAccountDTO, UserAccountDTO.class);
    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }
}
