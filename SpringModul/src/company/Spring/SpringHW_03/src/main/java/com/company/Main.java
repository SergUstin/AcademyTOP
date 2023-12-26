package com.company;

import com.company.config.AppConfig;
import com.company.service.ContactsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        context.getBean(ContactsService.class).fabricMethod();

    }
}
