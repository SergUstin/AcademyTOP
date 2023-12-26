package com.company;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PrototypeComponent {

    private Instant createdTime;

    public PrototypeComponent() {
        System.out.println("Prototype created");
        this.createdTime = Instant.now();
        printCreatedTime();
    }

    public void printCreatedTime() {
        System.out.println("Prototype created at: " + createdTime);
    }
}
