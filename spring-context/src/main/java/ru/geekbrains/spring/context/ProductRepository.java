package ru.geekbrains.spring.context;

import java.util.List;

public interface ProductRepository {

    List<Product> getProductList();

    Product getProductById(Long id);
}
