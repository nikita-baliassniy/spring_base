package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getProductList();

    Product getProductById(Long id);

    void add(Product product);

    Product saveOrUpdateProduct(Product newProduct);
}
