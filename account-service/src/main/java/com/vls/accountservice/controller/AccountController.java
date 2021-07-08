package com.vls.accountservice.controller;

import com.vls.accountservice.module.Account;
import com.vls.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Account>> getAllUser() {
        try {
            List<Account> users = accountRepository.ListAllUser();

            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Account> createTutorial(@RequestBody String email) {
        if (checked(email)){
            Account user = new Account();
            user.setEmail(email);
            try {
                Account _user = accountRepository.save(user);
                return new ResponseEntity<>(_user, HttpStatus.CREATED);
                //return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{email}")
    public ResponseEntity<Account> userProfile(@PathVariable String email) {
        Account info = accountRepository.giveAccountInfo(email);
        if(info.getEmail().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity( info.getEmail() ,HttpStatus.OK);
    }
    public boolean checked(String email){
        List<Account> checkList = accountRepository.CheckedUser(email);
        if (checkList.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

}
