package com.geekbrains.hibernate.crud;

import com.geekbrains.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static List<Product> findAll() {
        List<Product> allProducts = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            allProducts = session.createQuery("from Product").getResultList();
            System.out.println(allProducts);
            session.getTransaction().commit();
        }
        return allProducts;
    }

    public static Product saveOrUpdate(Product product) {
        try (Session session = factory.getCurrentSession()) {
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

    public static void findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }

    public static void main(String[] args) {
        try {
            init();
            findAll();
            findById(4L);
            saveOrUpdate(new Product( "apple", 12.00));
            saveOrUpdate(new Product(1L, "milk", 9.77));
            findAll();
            deleteById(5L);
            findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }


}
