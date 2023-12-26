package com.company;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Scope("singleton")
@Component
public class FirstSingleton {

    private Instant createdTime;

    public FirstSingleton() {
        System.out.println("FirstSingleton created");
        this.createdTime = Instant.now();
        printCreatedTime();
    }

    public void printCreatedTime() {
        System.out.println("FirstSingleton created at: " + createdTime);
    }
}
