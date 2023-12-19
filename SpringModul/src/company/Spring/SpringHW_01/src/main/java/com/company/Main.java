package company.Spring.SpringHW_01.src.main.java.com.company;


import company.Spring.SpringHW_01.src.main.java.com.company.config.AppConfig;
import company.Spring.SpringHW_01.src.main.java.com.company.service.NotificationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        context.getBean(NotificationService.class).sendNotification();
    }

}
