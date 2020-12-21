package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductsRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProductList();
    }

    public Product findById(Long id) {
        return productRepository.getProductById(id);
    }

    public Product saveOrUpdateProduct(Product newProduct) {
        return productRepository.saveOrUpdateProduct(newProduct);
    }
}
