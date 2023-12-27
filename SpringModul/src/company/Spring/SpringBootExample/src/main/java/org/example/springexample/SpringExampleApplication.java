package org.example.springexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringExampleApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringExampleApplication.class, args);

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        System.out.println("Количество бинов: " + beanNames.length);

//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }
}
