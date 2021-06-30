package com.vls.adminservice.controller;

import ch.qos.logback.classic.Logger;
import com.vls.adminservice.AdminServiceApplication;
import com.vls.adminservice.Module.Admin_account;
import com.vls.adminservice.repository.admin_accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
public class admin_login_controller {
    @Autowired
    private admin_accountRepository admin_accRepo;

    @RequestMapping("/login")
    public String Checked_admin(HttpSession session) {
        String temp = "";
        String users_admin = "okadatoru";
        String password = "12345";

        List<Admin_account> listAcc = admin_accRepo.checked(users_admin, password);
        if (!listAcc.isEmpty() && listAcc.size() == 1) {
            for (Admin_account acc: listAcc) {
                Admin_account account = new Admin_account(acc);
                if ( session == null){
                    session.setAttribute("id",account.getUUID());
                }
                else {
                    session = null;
                }
                temp = "id : " + account.getUUID();
            }
            return temp;

        } else {
            return "fail conn";
        }
    }
    @RequestMapping("/logout")
    public String log_out(HttpServletRequest request){
        try {
            request.getSession().invalidate();
            return "logout";
        }catch (Exception e){
            return "No Session to destroys" + e;
        }
    }

}
