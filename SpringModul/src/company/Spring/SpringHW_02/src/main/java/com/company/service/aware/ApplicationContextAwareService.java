package company.Spring.SpringHW_02.src.main.java.com.company.service.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextAwareService implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void displayApplicationInfo() {
        String applicationName = applicationContext.getApplicationName();
        System.out.println("Application Name: " + applicationName);
    }
}
