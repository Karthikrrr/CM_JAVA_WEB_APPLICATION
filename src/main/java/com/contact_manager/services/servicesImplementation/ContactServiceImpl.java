package com.contact_manager.services.servicesImplementation;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.contact_manager.Exception.ResourceNotFoundException;
import com.contact_manager.entities.Contact;
import com.contact_manager.entities.User;
import com.contact_manager.repositories.ContactRepository;
import com.contact_manager.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact saveContact(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId); 
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(String id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Not Found With Given Id : " + id));
    }

    @Override
    public void deleteContact(String id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Not Found With Given Id : " + id));
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> searchContacts(String name, String email, String phoneNumber) {
        throw new UnsupportedOperationException("Unimplemented method 'searchContacts'");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public List<Contact> getByUser(User user) {
        return contactRepository.findByUser(user);
    }

}
