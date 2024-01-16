package com.company.contactsbook.controller;

import com.company.contactsbook.model.Contact;
import com.company.contactsbook.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String showContacts(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        log.info("Retrieved contacts from the database.");
        return "contacts";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "create";
    }

    @PostMapping("/create")
    public String createContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        log.info("Created contact: {}", contact.toString());
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact ID: " + id));
        model.addAttribute("contact", contact);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id);
        contactRepository.save(contact);
        log.info("Edited contact with ID {}:{}", id, contact.toString());
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        log.info("Deleted contact with ID {}", id);
        return "redirect:/contacts";
    }
}
