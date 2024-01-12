package com.company.springexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class SpringDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDBCApplication.class, args);
        log.info("Application started");
    }
}
