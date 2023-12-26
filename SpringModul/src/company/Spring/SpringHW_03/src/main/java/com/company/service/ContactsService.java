package com.company.service;

import com.company.model.Contact;
import com.company.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ContactsService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactsService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void fabricMethod() {
        Contact contact1 = new Contact("Serg", "+7(999) 999-99-99", "serg@example.com");
        Contact contact2 = new Contact("Roman", "+7(999) 999-99-98", "roman@example.com");
        Contact contact3 = new Contact("Oleg", "+7(999) 999-99-97", "oleg@example.com");

        // Загрузить контакты из файла
//        try {
//            System.out.println("Получение контактов из файла!");
//            initializeFromContactsFile("contact.txt");
//            System.out.println();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // Добавление контактов
        System.out.println("Добавление контактов!");
        addContact(contact1);
        addContact(contact2);
        addContact(contact3);
        System.out.println("Контакты успешно добавлены!");
        System.out.println();

        // Удаление контакта по email
        System.out.println("Удаление контакта");
        removeContact("roman@example.com");
        System.out.println();

        // Получить контакты
        System.out.println("Получение всех контактов! (работа метода getAllContacts())");
        for (Contact contact : contactRepository.getAllContacts()) {
            System.out.println(contact.getFullName() + " | " + contact.getPhoneNumber() + " | " +
                    contact.getEmail());
        }
        System.out.println();

        // Сохранить контакты в файл
        try {
            System.out.println("Сохранение контактов!");
            saveContactsToFile("contact.txt");
            System.out.println();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Вывести контакты из списка
        System.out.println("Вывод контактов в консоль");
        printContact();

    }

    public void addContact(Contact contact) {
        contactRepository.addContact(contact);
    }

    public void removeContact(String email) {
        contactRepository.removeContact(email);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.getAllContacts();
    }

    public void saveContactsToFile(String fileName) throws IOException {
        contactRepository.saveContactsToFile(fileName);
    }

    public List<Contact> initializeFromContactsFile(String fileName) throws IOException {
        return contactRepository.initializeFromContactsFile(fileName);
    }

    public void printContact() {
        for (Contact contact : contactRepository.getAllContacts()) {
            String formattedContact = contact.getFullName() + " | " + contact.getPhoneNumber() + " | " +
                    contact.getEmail();
            System.out.println(formattedContact);
        }
    }


}
