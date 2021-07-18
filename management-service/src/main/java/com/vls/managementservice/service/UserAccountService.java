package com.vls.managementservice.service;

import com.vls.managementservice.model.UserAccount;
import com.vls.managementservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    
    public Optional<UserAccount> findUser(UUID userId) {
        return userAccountRepository.findById(userId);
    }

    public List<UserAccount> getList() {
        return userAccountRepository.findAll();
    }

    public UserAccount block(UUID userId) {
        Optional<UserAccount> foundUser = findUser(userId);
        UserAccount user = foundUser.get();
        user.setBlock(!user.isBlock());
        return userAccountRepository.save(user);
    }
}
