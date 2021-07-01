package com.vls.adminservice.controller;

import com.vls.adminservice.Module.Admin_account;
import com.vls.adminservice.repository.admin_accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class admin_login_controller {
    @Autowired
    private admin_accountRepository admin_accRepo;

    @RequestMapping("/login")
    public ModelAndView Checked_admin(HttpSession session, @RequestParam(name = "username") String username , @RequestParam(name = "pwd") String pwd ) {
        String users_admin = username; //okadatoru
        String password = pwd; //12345

        List<Admin_account> listAcc = admin_accRepo.checked(users_admin, password);
        if (!listAcc.isEmpty() && listAcc.size() == 1) {
            for (Admin_account acc: listAcc) {
                Admin_account account = new Admin_account(acc);
                session.setAttribute("id",account.getUUID());
            }
            return new ModelAndView("Welcome") ;

        } else {
            return new ModelAndView("errorLogin_admin");
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
    @RequestMapping("/")
    public ModelAndView hello(){
        //ModelAndView mav = new ModelAndView();
        return new ModelAndView("signin_admin");
    }


}
