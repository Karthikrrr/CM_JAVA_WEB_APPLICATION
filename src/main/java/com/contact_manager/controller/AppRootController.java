package com.contact_manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.contact_manager.entities.User;
import com.contact_manager.helper.Helper;
import com.contact_manager.services.UserService;

@ControllerAdvice
public class AppRootController {
    
    private final Logger logger = LoggerFactory.getLogger(AppRootController.class);

    private final UserService userService;

    public AppRootController(UserService userService){
        this.userService = userService;
    }

     @ModelAttribute
    public void addLoggedUserInfo(Model model, Authentication authentication){
        if(authentication == null) return;
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(userName);
        logger.info("Name : " + user.getName());
        logger.info("Email : " + user.getEmail());
        logger.info("User : {}", userName);
        model.addAttribute("loggedInUser", user);
    }
}
