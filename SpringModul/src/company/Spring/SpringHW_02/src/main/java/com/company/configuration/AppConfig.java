package company.Spring.SpringHW_02.src.main.java.com.company.configuration;


import company.Spring.SpringHW_02.src.main.java.com.company.service.cycle.LoggingService;
import company.Spring.SpringHW_02.src.main.java.com.company.service.scope.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

@ComponentScan("com.company")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean propertiesFactoryBean = new YamlPropertiesFactoryBean();
        propertiesFactoryBean.setResources(new ClassPathResource("application.yml"));

        configurer.setProperties(Objects.requireNonNull(propertiesFactoryBean.getObject()));

        return configurer;
    }

    @Bean
    @Scope()
    @Qualifier
    public UserService singletonUserService() {
        System.out.println("singleton");
        return new UserService();
    }

    @Bean
    @Scope("prototype")
    @Primary
    public UserService prototypeUserService() {
        System.out.println("prototype");
        return new UserService();
    }

    @Bean
    public LoggingService loggingService() {
        return new LoggingService("Service name");
    }
}
