package com.vls.admin_usermanagementservice.service;

import com.vls.admin_usermanagementservice.model.UserAccount;
import com.vls.admin_usermanagementservice.repository.UserAccountRepository;
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

    public UserAccount block(UserAccount user) {
        Optional<UserAccount> findedUser = findUser(user.getId());
        user.setBlock(!findedUser.get().isBlock());
        return userAccountRepository.save(user);
    }
}
