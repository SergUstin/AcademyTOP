package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        ObjectFactoryHolder factoryHolder = context.getBean(ObjectFactoryHolder.class);
//
//        System.out.println(factoryHolder.getPrototypeComponent() == factoryHolder.getPrototypeComponent());
//        System.out.println(factoryHolder.getSingleton() == factoryHolder.getSingleton());

//        FirstSingleton singleton1 = context.getBean(FirstSingleton.class);
//        FirstSingleton singleton2 = context.getBean(FirstSingleton.class);
//
//        System.out.println(singleton1 == singleton2);
    }
}
