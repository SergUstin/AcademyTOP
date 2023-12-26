package com.company.service;

import com.company.model.Contact;
import com.company.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void call() {
        // Добавим возможность добавления контакта
        Contact contact = new Contact("Roman", "+79306786778", "roman@example.com");
        addContact(contact);

        try {
            saveContactToFile("contact.xtx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addContact(Contact contact) {
        contactRepository.addContact(contact);
    }

    public void deleteContactByEmail(String email) {
        contactRepository.deleteContactByEmail(email);
    }

    public void saveContactToFile(String fileName) throws IOException {
        contactRepository.saveContactsToFile(fileName);
    }

    public void printContacts(Contact contact) {
        contactRepository.getAllContacts();
    }


}
