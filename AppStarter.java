package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        CartManager cartManager = context.getBean(CartManager.class);
    }

}
