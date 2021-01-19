package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void addProduct(Product newProduct) {
        productRepository.save(newProduct);
    }
}
