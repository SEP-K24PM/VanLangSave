package com.vls.adminservice.controller;

import com.vls.adminservice.Module.Admin_account;
import com.vls.adminservice.repository.admin_accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class admin_login_controller {
    @Autowired
    private admin_accountRepository admin_accRepo;

    @GetMapping("/admin_acc")
    public String listAll() {
        String users_admin = "okadatoru";
        String password = "12345";

        List<Admin_account> listAcc = admin_accRepo.checked(users_admin,password);

        if (listAcc.size() > 0 & listAcc.size() <2 ){
            return "succes";
        }else{
            return "fail";
        }

    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
