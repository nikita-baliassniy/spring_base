package ru.geekbrains.hibernate2_spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.hibernate2_spring.context.services.ClientProductService;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        ClientProductService service = context.getBean("clientProductService",
                ClientProductService.class);
        service.getClientsByProducts(5L);
        service.getProductsByClint(2L);
        context.close();
    }
}
