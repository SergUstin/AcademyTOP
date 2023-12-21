package company.Spring.SpringHW_02.src.main.java.com.company.service.scope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class UserService {
    @Value("${app.user.name}")
    private String name;

    public void getRandomNumber() {
        System.out.println(name + " " + Math.round(Math.random() * 10));
    }





}
