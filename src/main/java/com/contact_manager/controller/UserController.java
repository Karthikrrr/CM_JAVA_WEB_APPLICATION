package com.contact_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
    public String userDashboard() {
        return "users/dashboard";
    }
    
    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile() {
        return "users/profile";
    }
    
}
