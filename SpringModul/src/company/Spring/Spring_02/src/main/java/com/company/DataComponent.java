package com.company;

import org.springframework.stereotype.Component;

@Component // С помощью этой аннотации помещаем bean данного класса в контейнер spring`а
public class DataComponent {

    public DataComponent() {
        System.out.println("Data component init...");
    }

    public void someWork() {
        System.out.println("Some component work...");
    }
}
