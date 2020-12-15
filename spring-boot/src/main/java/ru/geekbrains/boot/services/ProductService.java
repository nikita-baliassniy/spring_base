package ru.geekbrains.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.repositories.ProductInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductInMemoryRepository productInMemoryRepository;

    public Optional<Product> findById(Long id) {
        return productInMemoryRepository.findById(id);
    }

    public List<Product> findAll() {
        return productInMemoryRepository.findAll();
    }

    public List<Product> findAll(Integer minCost, Integer maxCost) {
        List<Product> out = findAll();
        if (minCost != null) {
            out = out.stream().filter(s -> s.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(s -> s.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

    public Product saveOrUpdate(Product s) {
        return productInMemoryRepository.saveOrUpdate(s);
    }

    public void deleteBydId(Long id) {
        productInMemoryRepository.deleteById(id);
    }
}
