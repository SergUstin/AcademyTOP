package company.Spring.SpringHW_02.src.main.java.com.company.service.cycle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class LoggingService  {
    @Value("${app.service.name}")
    private String serviceName;

    public LoggingService(String serviceName) {
        this.serviceName = serviceName;
    }

    @PostConstruct
    public void init() {
        System.out.println("LoggingService initialized..." + serviceName);
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("LoggingService destroy..." + serviceName);
    }
}
