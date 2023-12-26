package com.company.service.repository;

import com.company.model.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactRepository {
    List<Contact> getAllContacts();
    void addContact(Contact contact);
    void removeContact(String email);
    void saveContactsToFile(String fileName) throws IOException;
    List<Contact> initializeFromContactsFile(String fileName) throws IOException;
}
