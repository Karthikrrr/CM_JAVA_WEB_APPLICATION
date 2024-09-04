package com.contact_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact_manager.entities.User;
import com.contact_manager.forms.UserForm;
import com.contact_manager.helper.Message;
import com.contact_manager.helper.MessageType;
import com.contact_manager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String registerProcess(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession httpSession ) {
        // User user =
        // User.builder().name(userForm.getName()).email(userForm.getEmail()).password(userForm.getPassword())
        // .about(userForm.getAbout()).phoneNumber(userForm.getPhoneNumber()).build();

        if(bindingResult.hasErrors()){
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());

        userService.saveUser(user);

        Message registerSucessMessage = Message.builder().content("Register Succesful").type(MessageType.green).build();

        httpSession.setAttribute("message", registerSucessMessage);
        return "redirect:/register";
    }

}
