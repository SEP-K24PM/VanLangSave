package com.vls.managementservice.service;

import com.vls.managementservice.model.User_Account;
import com.vls.managementservice.repository.User_AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class User_AccountService {
    private final User_AccountRepository userAccountRepository;

    @Autowired
    public User_AccountService(User_AccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Optional<User_Account> getAccount(String email) {
        return userAccountRepository.findAccountByEmailEquals(email);
    }

    public User_Account saveAccount(User_Account user_account) {
        return userAccountRepository.save(user_account);
    }
}
