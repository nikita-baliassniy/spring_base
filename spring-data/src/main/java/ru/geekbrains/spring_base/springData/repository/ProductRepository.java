package ru.geekbrains.spring_base.springData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring_base.springData.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostIsGreaterThan(double minCost);

    List<Product> findAllByCostIsLessThan(double maxCost);

    List<Product> findAllByCostBetween(double minCost, double maxCost);
}
