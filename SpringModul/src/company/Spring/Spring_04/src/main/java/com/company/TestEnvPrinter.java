package com.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestEnvPrinter implements EnvPrinter {
    @Value("${app.env}")
    private String env;

    @Override
    public void printEnv() {
        System.out.println("Calling TestEnvPrinter printEnv");
        System.out.println("TestEnvPrinter env is: " + env);
    }
}
