package com.vls.admin_usermanagementservice.controller;

import com.vls.admin_usermanagementservice.model.UserAccount;
import com.vls.admin_usermanagementservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserManagementController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserManagementController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<List<UserAccount>> listUserAccount(){
        List<UserAccount> list = userAccountService.getList();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/block",method = RequestMethod.POST)
    public ResponseEntity<UserAccount> blockUser(@RequestBody UserAccount user){
        UserAccount updatedUser = userAccountService.block(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
