package com.vls.accountservice.controller;

import com.vls.accountservice.model.Account;
import com.vls.accountservice.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountController(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/profile/{id}")
    public ResponseEntity<Account> userProfile(@PathVariable String id) {
        var uid = UUID.fromString(id);
        Account info = accountRepository.giveAccountInfo(uid);
//        UserAccountDTO
        if(info.getEmail() != null) {
            return new ResponseEntity( info ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
