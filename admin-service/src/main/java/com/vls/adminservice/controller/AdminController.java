package com.vls.adminservice.controller;

import Constants.AdminApiConstants;
import DTO.PostDTO;
import com.vls.adminservice.model.Admin_Account;
import com.vls.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

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

    @RequestMapping(value = "/hidepost/{postId}", method = RequestMethod.POST)
    public ResponseEntity<PostDTO> hidePost(@PathVariable("postId") String postId) {
        PostDTO result = restTemplate.postForObject(AdminApiConstants.PostManage.HIDE_POST, postId, PostDTO.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
