package com.contact_manager.services.servicesImplementation;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<Contact> getByUserId(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Contact> searchContactsByName(String name, int size, int page, String sortBy, String order) {
        Sort nameSort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pagable = PageRequest.of(page, size, nameSort);
        return contactRepository.findByNameContaining(name, pagable);
    }

    @Override
    public Page<Contact> searchContactsByEmail(String email, int size, int page, String sortBy, String order) {
        Sort emailSort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pagable = PageRequest.of(page, size, emailSort);
        return contactRepository.findByEmailContaining(email, pagable);
    }

    @Override
    public Page<Contact> searchContactsByPhoneNumber(String phone, int size, int page, String sortBy, String order) {
       Sort phoneNumberSort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
       PageRequest pageable  = PageRequest.of(page, size, phoneNumberSort);
       return contactRepository.findByPhoneNumberContaining(phone, pageable);
    }

}
 