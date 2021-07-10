package com.vls.adminservice.service;

import com.vls.adminservice.model.Admin_Account;
import com.vls.adminservice.repository.AdminAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminAccountRepository adminAccountRepository;

    @Autowired
    public AdminService(AdminAccountRepository adminAccountRepository) {
        this.adminAccountRepository = adminAccountRepository;
    }

    public Optional<Admin_Account> getAccount(String email) {
        return adminAccountRepository.findAdmin_AccountByEmailEquals(email);
    }

}
