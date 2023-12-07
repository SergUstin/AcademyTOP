package com.compony;

public class Main {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();

        beanFactory.register(Injectable.class);
        beanFactory.register(Source.class);

        Source source = beanFactory.getBean(Source.class);

        source.call();
    }
}
