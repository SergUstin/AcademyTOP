package company.Spring.SpringHW_01.src.main.java.com.company.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements MessagingService {
    @Override
    public void sendMessage() {
        System.out.println("Sending a message by mail");

    }
}
