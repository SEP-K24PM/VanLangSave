package com.vls.newsfeedservice.service;

import com.vls.newsfeedservice.model.UserAccount;
import com.vls.newsfeedservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Optional<UserAccount> getUserAccount(UUID userId) {
        return userAccountRepository.findById(userId);
    }
}
