package com.vls.adminservice.controller;

import com.vls.adminservice.model.Admin_Account;
import com.vls.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Admin_Account> login(@RequestBody Admin_Account adminAccount) {
        Optional<Admin_Account> admin = adminService.getAccount(adminAccount.getEmail());
        if(admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
