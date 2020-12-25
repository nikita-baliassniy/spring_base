package ru.geekbrains.hibernate2_spring.context.DAO;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.geekbrains.hibernate2_spring.context.interfaces.ClientRepository;
import ru.geekbrains.hibernate2_spring.context.model.Client;
import ru.geekbrains.hibernate2_spring.context.model.Product;
import ru.geekbrains.hibernate2_spring.context.services.SessionManager;

import java.util.ArrayList;
import java.util.List;


@Component
public class ClientDBRepository implements ClientRepository {

    @Override
    public List<Client> findAll() {
        List<Client> allClients = new ArrayList<>();
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            allClients = session.createQuery("from Client").getResultList();
            session.getTransaction().commit();
        }
        return allClients;
    }

    @Override
    public Client saveOrUpdate(Client client) {
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            if (client.getId() == null) {
                session.save(client);
            } else {
                session.update(client);
            }
            session.getTransaction().commit();
        }
        return client;
    }

    @Override
    public Client findById(Long id) {
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.getTransaction().commit();
            return client;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.delete(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Product> findProductsByClientId(Long id) {
        List<Product> allProducts = new ArrayList<>();
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            allProducts = client.getProducts();
            session.getTransaction().commit();
        }
        return allProducts;
    }
}
