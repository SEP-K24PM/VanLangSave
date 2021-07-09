package com.vls.accountservice.service;

import com.vls.accountservice.module.User_Account;
import com.vls.accountservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Optional<User_Account> getAccount(String email) {
        return userAccountRepository.findAccountByEmailEquals(email);
    }

    public User_Account saveAccount(User_Account user_account) {
        return userAccountRepository.save(user_account);
    }
}
