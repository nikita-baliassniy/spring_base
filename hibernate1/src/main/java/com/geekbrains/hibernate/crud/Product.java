package com.geekbrains.hibernate.crud;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int price) {
        this.cost = price;
    }

    public Product() {
    }

    public Product(Long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("[Product %d, title: %s, cost: %f]", id, title, cost);
    }
}