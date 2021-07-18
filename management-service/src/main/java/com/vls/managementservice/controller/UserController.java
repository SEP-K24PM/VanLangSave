package com.vls.managementservice.controller;

import java.util.*;

import DTO.UserAccountDTO;
import com.vls.managementservice.model.Account;
import com.vls.managementservice.model.User_Account;
import com.vls.managementservice.repository.AccountRepository;
import com.vls.managementservice.service.UserService;
import com.vls.managementservice.service.User_AccountService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {
    private final User_AccountService userAccountService;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(User_AccountService user_AccountService, AccountRepository accountRepository,
                          ModelMapper modelMapper, UserService userService) {
        this.userAccountService = user_AccountService;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User_Account> login(@RequestBody User_Account userAccount) {
        if(userAccount.getEmail().endsWith("@vanlanguni.vn")) {
            Optional<User_Account> user = userAccountService.getAccount(userAccount.getEmail());
            if(user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
            } else {
                User_Account user_account = new User_Account(userAccount.getEmail(), false);
                User_Account savedUser = userAccountService.saveAccount(user_account);
                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/profile/{id}")
    public ResponseEntity<UserAccountDTO> userProfile(@PathVariable("id") String id) {
        Account info = accountRepository.giveAccountInfo(UUID.fromString(id));
        UserAccountDTO userAccountDTO = modelMapper.map(info, UserAccountDTO.class);
        userAccountDTO.setUserRatingList(userService.setRatingList(info.getRatings()));
        userAccountDTO.setPostList(userService.getListPostByUser(info));
        if(info.getEmail() != null) {
            return new ResponseEntity<>(userAccountDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
