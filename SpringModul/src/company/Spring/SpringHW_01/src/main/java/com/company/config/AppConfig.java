package company.Spring.SpringHW_01.src.main.java.com.company.config;

import company.Spring.SpringHW_01.src.main.java.com.company.service.MessagingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import company.Spring.SpringHW_01.src.main.java.com.company.service.EmailService;
import company.Spring.SpringHW_01.src.main.java.com.company.service.SMSService;

@ComponentScan("com.company")
public class AppConfig {

    @Bean
    @Primary
    public MessagingService sendEmail() {
        return new EmailService();
    }

    @Bean
    public MessagingService sendSMS() {
        return new SMSService();
    }
}
