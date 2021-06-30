package com.vls.adminservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {
    @RequestMapping("/session")
    public String helloAdmin() {
        return "hello, i'm session";
    }
    @RequestMapping("/check_session")
    public String check_session(HttpServletRequest request){
        try {
            String temp = "";
            temp = request.getSession().getAttribute("id").toString();
            return temp;
        }catch (Exception e){
            return "No session defination";
        }
    }
    @RequestMapping("/del_session")
    public String del_session(HttpServletRequest request){
        try {
            request.getSession().invalidate();
            return "Session Destroys";
        }catch (Exception e){
            return "No Session to destroys";
        }
    }
}
