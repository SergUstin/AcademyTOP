package company.Spring.SpringHW_02.src.main.java;

import company.Spring.SpringHW_02.src.main.java.com.company.configuration.AppConfig;
import company.Spring.SpringHW_02.src.main.java.com.company.service.aware.ApplicationContextAwareService;
import company.Spring.SpringHW_02.src.main.java.com.company.service.cycle.LoggingService;
import company.Spring.SpringHW_02.src.main.java.com.company.service.scope.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Создайте два бина с разными скоупами (singleton и prototype).
        context.getBean(UserService.class).getRandomNumber();
        context.getBean(UserService.class).getRandomNumber();
        context.getBean(UserService.class).getRandomNumber();
        context.getBean(UserService.class).getRandomNumber();

        // Создайте класс LoggingService с полем serviceName
        // Настройка свойств через YAML файл
        LoggingService loggingService = context.getBean(LoggingService.class);
        ((AbstractApplicationContext) context).getBeanFactory().destroyBean("loggingService", loggingService);

        // Aware интерфейсы
        System.out.println("Aware data output");
        context.getBean(ApplicationContextAwareService.class).displayApplicationInfo();


    }
}
