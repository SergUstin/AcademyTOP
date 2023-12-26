package com.company;

import org.springframework.stereotype.Component;

@Component
public class ProfileWorker {

    private final EnvPrinter envPrinter;

    public ProfileWorker(EnvPrinter envPrinter) {
        this.envPrinter = envPrinter;
    }

    public void doWork() {
        envPrinter.printEnv();
    }
}
