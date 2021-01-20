package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDto> getAllProducts(Double minCost, Double maxCost, int page) {
        Page<Product> out;
        Pageable pageRequest = PageRequest.of(page - 1, 10);
        if (minCost != null && maxCost != null) {
            out = productRepository.findAllByCostBetween(minCost, maxCost, pageRequest);
        } else if (minCost != null) {
            out = productRepository.findAllByCostIsGreaterThan(minCost, pageRequest);
        } else if (maxCost != null) {
            out = productRepository.findAllByCostIsLessThan(maxCost, pageRequest);
        } else {
            out = productRepository.findAll(pageRequest);
        }
        return new PageImpl<>(out.getContent().stream().map(ProductDto::new)
                .collect(Collectors.toList()));
    }

    public ProductDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductDto::new).orElseGet(ProductDto::new);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto saveOrUpdate(ProductDto product) {
        Optional<Product> productToUpdate = productRepository.findById(product.getId());
        Product productToAdd;
        if (product.getId() != null && productToUpdate.isPresent()) {
            productToAdd = productToUpdate.get();
        } else {
            productToAdd = new Product();
        }
        productToAdd.setTitle(product.getTitle());
        productToAdd.setCost(product.getCost());
        return new ProductDto(productRepository.save(productToAdd));
    }

}
