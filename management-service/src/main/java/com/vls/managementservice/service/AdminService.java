package com.vls.managementservice.service;

import com.vls.managementservice.model.AdminAccount;
import com.vls.managementservice.repository.AdminAccountRepository;
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

    public Optional<AdminAccount> getAccount(String email) {
        return adminAccountRepository.findAdmin_AccountByEmailEquals(email);
    }

}
