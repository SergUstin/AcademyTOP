package company.Spring.Spring_05.src.main.java.com.company.config;

import com.company.service.impl.InMemoryContactRepositoryImpl;
import com.company.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.company")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public ContactRepository contactRepository(@Value("contacts.file.path") String contactFilePath) {
        return new InMemoryContactRepositoryImpl();
    }
}
