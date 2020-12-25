package ru.geekbrains.hibernate2_spring.context.interfaces;

import ru.geekbrains.hibernate2_spring.context.model.Client;
import ru.geekbrains.hibernate2_spring.context.model.Product;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    List<Product> findProductsByClientId(Long id);

    Client saveOrUpdate(Client client);

    Client findById(Long id);

    void deleteById(Long id);
}
