package company.Spring.SpringHW_01.src.main.java.com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    private final EmailService emailService;

    private final PushNotificationService pushNotificationService;

    @Qualifier
    private final SMSService smsService;

    @Autowired
    public NotificationService(EmailService emailService, PushNotificationService pushNotificationService, SMSService smsService) {
        this.emailService = emailService;
        this.pushNotificationService = pushNotificationService;
        this.smsService = smsService;
    }

    public void sendNotification() {
        emailService.sendMessage();
        pushNotificationService.sendMessage();
        smsService.sendMessage();
    }

}
