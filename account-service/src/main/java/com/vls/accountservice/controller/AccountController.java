package com.vls.accountservice.controller;

import com.vls.accountservice.model.Account;
import com.vls.accountservice.model.Post;
import com.vls.accountservice.model.UserRating;
import com.vls.accountservice.repository.AccountRepository;
import com.vls.accountservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController

public class AccountController {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public AccountController(AccountRepository accountRepository, ModelMapper modelMapper, UserService userService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @RequestMapping(value = "/profile/{id}")
    public ResponseEntity<Account> userProfile(@PathVariable String id) {
        var uid = UUID.fromString(id);
        Account info = accountRepository.giveAccountInfo(uid);
        if(info.getEmail() != null) {
            return new ResponseEntity( info ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/profile/{id}/post")
    public ResponseEntity<List<Post>> getUserPost(@PathVariable String id){
        var uid = UUID.fromString(id);
        Account info = accountRepository.giveAccountInfo(uid);

        var listUserPost = userService.getListPostByUser(info);

        return new ResponseEntity<>(listUserPost, HttpStatus.OK);
    }
    @RequestMapping(value = "/profile/{id}/rate")
    public ResponseEntity<List<UserRating>> getUserRated(@PathVariable String id){
        var uid = UUID.fromString(id);
        Account info = accountRepository.giveAccountInfo(uid);

        var listUserRated = userService.getListRated(info);

        return new ResponseEntity<>(listUserRated, HttpStatus.OK);
    }


}
