package com.contact_manager.controller;

import com.contact_manager.entities.Contact;
import com.contact_manager.services.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private ContactService contactService;

    public ApiController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId){
        return contactService.getContactById(contactId);
    }
}
