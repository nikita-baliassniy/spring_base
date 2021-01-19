package ru.geekbrains.spring_base.springData.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring_base.springData.model.Product;
import ru.geekbrains.spring_base.springData.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProducts(Double minCost, Double maxCost) {
        List<Product> out = new ArrayList<>();
        if (minCost != null && maxCost != null) {
            out = productRepository.findAllByCostBetween(minCost, maxCost);
        } else if (minCost != null) {
            out = productRepository.findAllByCostIsGreaterThan(minCost);
        } else if (maxCost != null) {
            out = productRepository.findAllByCostIsLessThan(maxCost);
        } else {
            out = getAllProducts();
        }
        return out;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void addProduct(Product newProduct) {
        productRepository.save(newProduct);
    }
}
