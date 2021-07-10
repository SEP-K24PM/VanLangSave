package com.vls.accountservice.controller;

import com.vls.accountservice.model.User_Account;
import com.vls.accountservice.repository.UserAccountRepository;
import com.vls.accountservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAccountController {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountRepository userAccountRepository, UserAccountService userAccountService) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountService = userAccountService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<User_Account>> getAllUser() {
        try {
            List<User_Account> users = userAccountRepository.ListAllUser();
            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User_Account> login(@RequestBody User_Account userAccount) {
        Optional<User_Account> user = userAccountService.getAccount(userAccount.getEmail());
        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
        } else {
            User_Account user_account = new User_Account(userAccount.getEmail(), false);
            User_Account savedUser = userAccountService.saveAccount(user_account);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }
}
