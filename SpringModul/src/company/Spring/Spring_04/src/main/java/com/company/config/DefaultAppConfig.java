package com.company.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.company")
@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {
}
