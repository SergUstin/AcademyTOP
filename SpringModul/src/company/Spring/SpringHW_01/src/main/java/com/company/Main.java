package company.Spring.SpringHW_01.src.main.java.com.company;

import com.company.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.company.service.NotificationService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(NotificationService.class).sendNotification();
    }

}
