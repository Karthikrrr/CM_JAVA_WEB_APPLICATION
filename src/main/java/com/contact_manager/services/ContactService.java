package com.contact_manager.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.contact_manager.entities.Contact;
import com.contact_manager.entities.User;

public interface ContactService {

    Contact saveContact(Contact contact);

    Contact updateContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(String id);

    void deleteContact(String id);

    Page<Contact> searchContactsByName(String name, int size, int page, String sortBy, String order);

    Page<Contact> searchContactsByEmail(String email, int size, int page, String sortBy, String order);

    Page<Contact> searchContactsByPhoneNumber(String phone, int size, int page, String sortBy, String order);

    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);
}
