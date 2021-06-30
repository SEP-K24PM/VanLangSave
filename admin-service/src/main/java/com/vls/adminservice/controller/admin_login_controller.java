package com.vls.adminservice.controller;

import com.vls.adminservice.Module.Admin_account;
import com.vls.adminservice.repository.admin_accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class admin_login_controller {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("test", "test");
        return "login_demoTemp";
    }

    @Autowired
    private admin_accountRepository admin_accRepo;

    @GetMapping("/login")
    public String listAll() {
        String temp= "";
        String users_admin = "okadatoru";
        String password = "12345";

        List<Admin_account> listAcc = admin_accRepo.checked(users_admin,password);

        if (!listAcc.isEmpty() ) {
            return "welcome";
        }else{
            return "fail";
        }
    }

}
