package ru.geekbrains.hibernate2_spring.context.interfaces;

import ru.geekbrains.hibernate2_spring.context.model.Client;
import ru.geekbrains.hibernate2_spring.context.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    List<Client> findClientsByProductId(Long id);

    Product saveOrUpdate(Product product);

    Product findById(Long id);

    void deleteById(Long id);
}
