package com.vls.adminservice.controller;

import com.vls.adminservice.Module.Admin_account;
import com.vls.adminservice.repository.admin_accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class admin_login_controller {
    @Autowired
    private admin_accountRepository admin_accRepo;

    @RequestMapping("/welcome")
    public ModelAndView Checked_admin(HttpSession session, @RequestParam(name = "username") String username , @RequestParam(name = "pwd") String pwd ) {
        String users_admin = username; //okadatoru
        String password = pwd; //12345

        List<Admin_account> listAcc = admin_accRepo.checked(users_admin, password);
        if (listAcc.size() == 1) {
            for (Admin_account acc: listAcc) {
                Admin_account account = new Admin_account(acc);
                session.setAttribute("id",account.getUUID());
                session.setAttribute("name",account.getName());
            }
            return new ModelAndView("Welcome") ;

        } else {
            return new ModelAndView("errorLogin_admin");
        }
    }
    @RequestMapping("/logout")
    public String log_out(HttpServletRequest request) throws ServletException {
        request.getSession().invalidate();
        request.logout();
        return "signin_admin";
    }
    @RequestMapping("/")
    public ModelAndView hello(HttpServletRequest request){
        //ModelAndView mav = new ModelAndView()
        request.getSession().invalidate();
        return new ModelAndView("signin_admin");
    }
    /*
    @RequestMapping("/welcome/de")
    public ModelAndView findAdminByID(HttpSession session) {
        String id_admin = session.getId();
        List<Admin_account> info = admin_accRepo.findAdminByID(id_admin);
        if (info.size() == 1) {
            for (Admin_account acc: info) {
                Admin_account account = new Admin_account(acc);
                session.setAttribute("id",account.getUUID());
                session.setAttribute("name",account.getName());
            }
            return new ModelAndView("detail") ;

        } else {
            return new ModelAndView("errorLogin_admin");
        }
    }

     */

}
