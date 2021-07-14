package com.vls.admin_usermanagementservice.service;

import com.vls.admin_usermanagementservice.model.UserAccount;
import com.vls.admin_usermanagementservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public List<UserAccount> getList() {
        return userAccountRepository.findAll();
    }

    public UserAccount block(UserAccount user) {
        user.setBlock(!user.isBlock());
        return userAccountRepository.save(user);
    }
}
