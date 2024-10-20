package com.contact_manager.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact_manager.helper.Helper;



@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
    public String userDashboard() {
        return "users/dashboard";
    }
    
    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile(Authentication authentication) {
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User : {}", userName);
        return "users/profile";
    }
    
}
