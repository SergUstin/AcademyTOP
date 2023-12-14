package company.Spring.SpringHW_01.src.main.java.com.company.service;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationService implements MessagingService {
    @Override
    public void sendMessage() {
        System.out.println("The Push notification service is running");
    }
}
