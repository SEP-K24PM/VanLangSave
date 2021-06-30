package com.vls.managerservice.controller;

import com.vls.managerservice.module.Manager_module;
import com.vls.managerservice.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class Manager_controller {
    @Autowired
    private ManagerRepository managerRepository;

    @RequestMapping("/login")
    public String Checked_admin(HttpSession session) {
        String temp = "";
        String users_admin = "harukimurakami";
        String password = "12345";

        List<Manager_module> listAcc = managerRepository.checked(users_admin, password);
        if (!listAcc.isEmpty() && listAcc.size() == 1) {
            for (Manager_module info: listAcc) {
                Manager_module account = new Manager_module(info);
                session.setAttribute("id",account.getUUID());
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
    @RequestMapping("/check_")
    public String check_session(HttpServletRequest request){
        try {
            String temp = "";
            temp = request.getSession().getAttribute("id").toString();
            return temp;
        }catch (Exception e){
            return "No session defination";
        }
    }
}
