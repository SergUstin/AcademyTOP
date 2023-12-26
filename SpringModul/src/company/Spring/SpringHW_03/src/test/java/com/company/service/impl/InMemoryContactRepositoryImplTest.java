package com.company.service.impl;

import com.company.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class InMemoryContactRepositoryImplTest {

    private InMemoryContactRepositoryImpl contactRepository;

    @BeforeEach
    public void setup() {
        contactRepository = new InMemoryContactRepositoryImpl();
    }

    @Test
    public void testGetAllContacts() {
        // Arrange
        Contact contact1 = new Contact("John Doe", "123456789", "john@example.com");
        Contact contact2 = new Contact("Jane Smith", "987654321", "jane@example.com");
        contactRepository.addContact(contact1);
        contactRepository.addContact(contact2);

        // Act
        List<Contact> allContacts = contactRepository.getAllContacts();

        // Assert
        Assertions.assertEquals(Arrays.asList(contact1, contact2), allContacts);
    }

    @Test
    public void testAddContact() {
        // Arrange
        Contact contact = new Contact("John Doe", "123456789", "john@example.com");

        // Act
        contactRepository.addContact(contact);
        List<Contact> allContacts = contactRepository.getAllContacts();

        // Assert
        Assertions.assertEquals(contact, allContacts.get(0));
    }

    @Test
    public void testRemoveContact() {
        // Arrange
        Contact contact = new Contact("John Doe", "123456789", "john@example.com");
        contactRepository.addContact(contact);

        // Act
        contactRepository.removeContact("john@example.com");
        List<Contact> allContacts = contactRepository.getAllContacts();

        // Assert
        Assertions.assertTrue(allContacts.isEmpty());
    }

    @Test
    public void testSaveContactsToFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        Contact contact1 = new Contact("John Doe", "123456789", "john@example.com");
        Contact contact2 = new Contact("Jane Smith", "987654321", "jane@example.com");
        contactRepository.addContact(contact1);
        contactRepository.addContact(contact2);
        String fileName = tempDir.resolve("contacts.txt").toString();

        // Act
        contactRepository.saveContactsToFile(fileName);

        // Assert
        List<String> lines = Files.readAllLines(Path.of(fileName));
        Assertions.assertEquals(Arrays.asList("John Doe; 123456789; john@example.com", "Jane Smith; 987654321; jane@example.com"), lines);
    }

    @Test
    public void testInitializeFromContactsFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        String fileName = tempDir.resolve("contacts.txt").toString();
        Files.write(Path.of(fileName), Arrays.asList("John Doe; 123456789; john@example.com", "Jane Smith; 987654321; jane@example.com"));

        // Act
        List<Contact> contacts = contactRepository.initializeFromContactsFile(fileName);

        // Assert
        Assertions.assertEquals(2, contacts.size());
        Assertions.assertEquals("John Doe", contacts.get(0).getFullName());
        Assertions.assertEquals("123456789", contacts.get(0).getPhoneNumber());
        Assertions.assertEquals("john@example.com", contacts.get(0).getEmail());
        Assertions.assertEquals("Jane Smith", contacts.get(1).getFullName());
        Assertions.assertEquals("987654321", contacts.get(1).getPhoneNumber());
        Assertions.assertEquals("jane@example.com", contacts.get(1).getEmail());
    }
}