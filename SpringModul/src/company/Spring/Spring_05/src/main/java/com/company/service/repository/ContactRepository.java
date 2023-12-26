package com.company.service.repository;

import com.company.model.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactRepository {
    List<Contact> getAllContacts();
    void addContact(Contact contact);
    void deleteContactByEmail(String email);
    void saveContactsToFile(String file) throws IOException;
}
