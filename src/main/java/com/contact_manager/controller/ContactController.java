package com.contact_manager.controller;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact_manager.entities.Contact;
import com.contact_manager.entities.User;
import com.contact_manager.forms.ContactForm;
import com.contact_manager.helper.Helper;
import com.contact_manager.helper.Message;
import com.contact_manager.helper.MessageType;
import com.contact_manager.services.ContactService;
import com.contact_manager.services.ImageService;
import com.contact_manager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private final UserService userService;
    private final ContactService contactService;
    private final ImageService imageService;

    public ContactController(ContactService contactService, UserService userService, ImageService imageService) {
        this.contactService = contactService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/addContacts";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
            Authentication authentication, HttpSession httpSession) {
        String username = Helper.getEmailOfLoggedInUser(authentication);

        

        if (bindingResult.hasErrors()) {
            httpSession.setAttribute("message",
                    Message.builder().content("Please Correct The Errors").type(MessageType.red).build());
            return "user/addContacts";
        }
        String fileName = UUID.randomUUID().toString();
        String imageUrl = imageService.uploadImage(contactForm.getContactImage(), fileName);

        User user = userService.getUserByEmail(username);
        logger.info("File Name : {}", contactForm.getContactImage().getOriginalFilename());
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorate(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setPicture(imageUrl);
        contact.setCloudinaryImagePublicId(fileName);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        contactService.saveContact(contact);

        httpSession.setAttribute("message",
                Message.builder().content("Contact Added Succesfully!").type(MessageType.green).build());
        return "redirect:/user/contacts/add";
    }


    @RequestMapping
    public String viewContacts(Authentication authentication, Model model) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        List<Contact> contacts = contactService.getByUser(user);
        model.addAttribute("contacts", contacts);
        return "user/contacts";
    }
    

}
