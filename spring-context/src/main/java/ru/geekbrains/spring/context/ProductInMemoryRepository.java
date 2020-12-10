package ru.geekbrains.spring.context;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductInMemoryRepository implements ProductRepository {
    private List<Product> productList;

    @Override
    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productList.stream()
                .filter(p -> p.getId().equals(id)).findFirst();
        return product.orElse(null);
    }

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Apple", 2.4));
        productList.add(new Product(2L, "Orange", 5.2));
        productList.add(new Product(3L, "Tomato", 1.7));
        productList.add(new Product(4L, "Cucumber", 0.9));
        productList.add(new Product(5L, "Pear", 10.1));
    }

}
