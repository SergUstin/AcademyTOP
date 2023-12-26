package com.company.service.impl;

import com.company.model.Contact;
import com.company.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryContactRepositoryImpl implements ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    @Value("${contacts.file.path}")
    private String contactFilePath;

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void removeContact(String email) {
        Contact contactToRemove = null;
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            System.out.println("Контакт успешно удален!");
        } else {
            System.out.println("Контакт не найден!");
        }
    }

    @Override
    public void saveContactsToFile(String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName,false)) {
            List<Contact> contacts = getAllContacts();
            for (Contact contact : contacts) {
                String fullName = contact.getFullName();
                String phoneNumber = contact.getPhoneNumber();
                String email = contact.getEmail();
                String contactString = fullName + "; " + phoneNumber + "; " + email + "\n";
                fileWriter.write(contactString);
            }
            System.out.println("Контакты успешно сохранены в файле!");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов в файл!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> initializeFromContactsFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 3) {
                    String name = fields[0].trim();
                    String phoneNumber = fields[1].trim();
                    String email = fields[2].trim();
                    contacts.add(new Contact(name, phoneNumber, email));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Данные из файла успешно добавлены!");

        return contacts;
    }
}
