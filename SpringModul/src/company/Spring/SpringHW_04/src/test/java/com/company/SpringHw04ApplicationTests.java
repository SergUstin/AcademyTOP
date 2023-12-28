package com.company;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringHw04ApplicationTests {

    @Test
    void contextLoads() {
        // Проверяем успешную загрузку контекста Spring
    }

    @Test
    void mainMethodShouldNotThrowException() {
        // Проверяем, что метод main не выбрасывает исключение
        SpringHw04Application.main(new String[]{});
    }

}
