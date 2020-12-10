package ru.geekbrains.spring.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private ProductRepository productRepository;
    private List<Product> products;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
        products = new ArrayList<>();
    }

    public void addProductById(Long id) {
        if (productRepository.getProductById(id) != null) {
            products.add(productRepository.getProductById(id));
        } else {
            System.out.println("No such product available");
        }
    }

    public void deleteProductById(Long id) {
        if (productRepository.getProductById(id) != null) {
            products.stream().filter(p -> p.getId()
                    .equals(id)).findFirst().ifPresent(p -> products.remove(p));
        } else {
            System.out.println("No such product available");
        }
    }

    public void deleteAllProductsById(Long id) {
        if (productRepository.getProductById(id) != null) {
            for (int i = products.size() - 1; i >= 0; i--) {
                if (products.get(i).getId().equals(id)) {
                    products.remove(i);
                }
            }
        } else {
            System.out.println("No such product available");
        }
    }

    public void showProductsInCart() {
        System.out.println("Cart: ");
        products.forEach(System.out::println);
    }

}
