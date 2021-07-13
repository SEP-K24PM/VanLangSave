package com.vls.admin_usermanagementservice.controller;

import com.vls.admin_usermanagementservice.model.userAccount;
import com.vls.admin_usermanagementservice.repository.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Admin_userController {
    private final UserAccountRepo user_accountRepo;
    @Autowired
    public Admin_userController(UserAccountRepo user_accountRepo){
        this.user_accountRepo = user_accountRepo;
    }
    //hiển thị danh sách account
    @RequestMapping(value = "/account",method = RequestMethod.GET)
    public ResponseEntity<userAccount> listUserAccount(){
        var list = user_accountRepo.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    //block user
    @RequestMapping(value = "/account/block",method = RequestMethod.PUT)
    public ResponseEntity<userAccount> blockUser(@RequestBody userAccount user){
        var _temp = user;
        try{
            if (_temp.getId() != null){
                userAccount _getUpdate = _temp;
                _getUpdate.setEmail(_temp.getEmail());
                _getUpdate.setBlock(true);
                var saved = user_accountRepo.save(_getUpdate);
                return new ResponseEntity<>(saved, HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }
    //mở block user
    @RequestMapping(value = "/account/open",method = RequestMethod.PUT)
    public ResponseEntity<userAccount> openBlock(@RequestBody userAccount user){
        var _temp = user;
        try{
            if (_temp.getId() != null){
                userAccount _getUpdate = _temp;
                _getUpdate.setEmail(_temp.getEmail());
                _getUpdate.setBlock(false);
                var saved = user_accountRepo.save(_getUpdate);
                return new ResponseEntity<>(saved, HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }
}
