package com.contact_manager.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact_manager.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @SuppressWarnings("unused")
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    
    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        return "user/profile";
    }
    
}
