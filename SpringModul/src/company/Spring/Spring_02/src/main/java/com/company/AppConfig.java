package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.company")
public class AppConfig {

    @Bean // С помощью этой аннотации помещаем bean данного метода в контейнер spring`а
    public LogicClass simpleLogicClass() {
        return new LogicClass();
    }

    @Bean // С помощью этой аннотации помещаем bean данного метода в контейнер spring`а
    public LogicClass logicClassData() {
        return new LogicClass("Logic class", 52);
    }

}
