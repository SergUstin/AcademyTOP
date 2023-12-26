package com.company.config;

import com.company.EnvPrinter;
import com.company.TestEnvPrinter;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(
        value = {
                @PropertySource(value = "classpath:application-test.properties")
        }
)
@Profile("test")
public class TestAppConfig {

    @Bean
    public EnvPrinter envPrinter() {
        return new TestEnvPrinter();
    }
}
