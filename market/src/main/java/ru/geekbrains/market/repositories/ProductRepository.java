package ru.geekbrains.market.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCostIsGreaterThan(double minCost, Pageable pageRequest);

    Page<Product> findAllByCostIsLessThan(double maxCost, Pageable pageRequest);

    Page<Product> findAllByCostBetween(double minCost, double maxCost, Pageable pageRequest);


}