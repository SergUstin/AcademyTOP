package company.Spring.SpringHW_01.src.main.java.com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import company.Spring.SpringHW_01.src.main.java.com.company.service.EmailService;
import company.Spring.SpringHW_01.src.main.java.com.company.service.SMSService;

@ComponentScan("com.company")
public class AppConfig {

    @Bean
    public EmailService sendEmail() {
        return new EmailService();
    }

    @Bean
    @Primary
    public SMSService sendSMS() {
        return new SMSService();
    }
}
