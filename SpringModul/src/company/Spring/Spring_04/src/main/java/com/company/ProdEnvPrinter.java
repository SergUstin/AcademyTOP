package com.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProdEnvPrinter implements EnvPrinter {

    @Value("${app.env}")
    private String env;

    @Override
    public void printEnv() {
        System.out.println("ProdEnvPrinter colling");
        System.out.println("ProdEnvPrinter env is: " + env);
    }
}
