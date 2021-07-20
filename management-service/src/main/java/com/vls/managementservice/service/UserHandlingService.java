package com.vls.managementservice.service;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.UserHandling;
import com.vls.managementservice.repository.UserHandlingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHandlingService {
    private final UserHandlingRepository userHandlingRepository;

    @Autowired
    public UserHandlingService(UserHandlingRepository userHandlingRepository) {
        this.userHandlingRepository = userHandlingRepository;
    }

    public List<UserHandling> findUserHandlingByUserId(UUID userId) {
        return userHandlingRepository.findHandlingByUserId(userId);
    }

    public UserHandling saveHandling(UserHandling userHandling) {
        return userHandlingRepository.save(userHandling);
    }

}
