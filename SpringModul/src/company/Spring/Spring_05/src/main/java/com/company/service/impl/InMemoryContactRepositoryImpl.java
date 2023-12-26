package com.company.service.impl;

import com.company.model.Contact;
import com.company.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryContactRepositoryImpl implements ContactRepository {

    private final List<Contact> contactList = new ArrayList<>();

    @Value("${contacts.file.path}")
    private String contactFilePath;

    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    @Override
    public void deleteContactByEmail(String email) {

    }

    @Override
    public void saveContactsToFile(String file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Contact contact : contactList) {
                writer.write(contact.getFullName() + "; " + contact.getPhoneNumber() + "; " + contact.getEmail());
            }
        }
    }
}
