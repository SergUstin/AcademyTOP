package com.company.config;

import com.company.EnvPrinter;
import com.company.ProdEnvPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-prod.properties")
@Profile("prod")
public class ProdAppConfig {

    @Bean
    public EnvPrinter envPrinter() {
        return new ProdEnvPrinter();
    }
}
