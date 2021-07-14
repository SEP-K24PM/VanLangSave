package com.vls.adminservice.controller;

import Constants.AdminApiConstants;
import DTO.PostReportDTO;
import DTO.UserAccountDTO;
import com.vls.adminservice.model.Admin_Account;
import com.vls.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@RestController
public class AdminController {
    private final AdminService adminService;
    private final RestTemplate restTemplate;

    @Autowired
    public AdminController(AdminService adminService, RestTemplate restTemplate) {
        this.adminService = adminService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<Admin_Account> login(@RequestBody Admin_Account adminAccount) {
        Optional<Admin_Account> admin = adminService.getAccount(adminAccount.getEmail());
        if(admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/list-report")
    public ResponseEntity<List<PostReportDTO>> hidePost() {
        List<PostReportDTO> result = restTemplate.getForObject(AdminApiConstants.ReportManage.LIST_REPORT, List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/details-report/{reportId}")
    public ResponseEntity<PostReportDTO> hidePost(@PathVariable("reportId") String reportId) {
        PostReportDTO result = restTemplate.postForObject(AdminApiConstants.ReportManage.DETAILS, reportId, PostReportDTO.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/handle-report")
    public ResponseEntity<PostReportDTO> hidePost(@RequestBody PostReportDTO postReportDTO) {
        PostReportDTO result = restTemplate.postForObject(AdminApiConstants.ReportManage.HANDLE, postReportDTO, PostReportDTO.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/list-user")
    public ResponseEntity<List<UserAccountDTO>> listUser() {
        List<UserAccountDTO> result = restTemplate.getForObject(AdminApiConstants.UserManage.LIST_USER, List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/block-user")
    public ResponseEntity<UserAccountDTO> blockUser(@RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO result = restTemplate.postForObject(AdminApiConstants.UserManage.BLOCK_USER, userAccountDTO, UserAccountDTO.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
