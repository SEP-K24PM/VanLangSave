package com.vls.accountservice.controller;

import com.vls.accountservice.module.Account;
import com.vls.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8300")
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @GetMapping("/show")
    public ResponseEntity<List<Account>> getAllUser() {
        try {
            List<Account> users = accountRepository.ListAllUser();

            List<Account> temp = users;
            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/post")
    public ResponseEntity<Account> createTutorial() {
        Account user = new Account();
        user.setUser_name("1unreal.187pm14034@vanlanguni.vn");
        user.setPwd("12345");
        try {
            Account _user = accountRepository.save(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
