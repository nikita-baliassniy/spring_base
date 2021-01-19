package ru.geekbrains.hibernate2_spring.context.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SessionManager {

    private static SessionFactory factory;

    public static SessionFactory getInstance() {
        return factory;
    }

    @PostConstruct
    private void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void shutdown() {
        factory.close();
    }
}
