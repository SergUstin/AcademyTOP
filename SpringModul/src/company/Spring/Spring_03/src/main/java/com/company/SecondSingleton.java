package com.company;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Lazy
public class SecondSingleton {

    private Instant createdTime;
    private final PrototypeComponent component;

    private SecondSingleton(PrototypeComponent component) {
        System.out.println("SecondSingleton created");
        createdTime = Instant.now();
        printCreatedTime();
        this.component = component;
        component.printCreatedTime();
    }

    private void printCreatedTime() {
        System.out.println("SecondSingleton created at: " + createdTime);
    }
}
