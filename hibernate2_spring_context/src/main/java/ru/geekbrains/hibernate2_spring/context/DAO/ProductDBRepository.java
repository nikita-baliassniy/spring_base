package ru.geekbrains.hibernate2_spring.context.DAO;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.geekbrains.hibernate2_spring.context.interfaces.ProductRepository;
import ru.geekbrains.hibernate2_spring.context.model.Client;
import ru.geekbrains.hibernate2_spring.context.model.Product;
import ru.geekbrains.hibernate2_spring.context.services.SessionManager;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductDBRepository implements ProductRepository {

    @PostConstruct
    public void init() {
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProducts = new ArrayList<>();
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            allProducts = session.createQuery("from Product").getResultList();
            System.out.println(allProducts);
            session.getTransaction().commit();
        }
        return allProducts;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            System.out.println(product);
            session.beginTransaction();
            if (product.getId() == null) {
                session.save(product);
            } else {
                session.update(product);
            }
            session.getTransaction().commit();
            System.out.println(product);
        }
        return product;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Client> findClientsByProductId(Long id) {
        List<Client> allBuyers = new ArrayList<>();
        try (Session session = SessionManager.getInstance().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            allBuyers = product.getClients();
            session.getTransaction().commit();
        }
        return allBuyers;
    }
}
