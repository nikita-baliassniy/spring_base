package ru.geekbrains.hibernate2_spring.context.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_clients",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("[Product %d, title: %s, cost: %f]", id, title, cost);
    }
}