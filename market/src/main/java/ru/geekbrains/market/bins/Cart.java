package ru.geekbrains.market.bins;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.dto.ProductDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class Cart {
    private Map<ProductDto, Integer> productsInCart;

    @PostConstruct
    private void init() {
        productsInCart = new HashMap<>();
    }

    public void addToCart(ProductDto product) {
        productsInCart.put(product, productsInCart.getOrDefault(product, 0) + 1);
    }

    public List<CartDto> getCart() {
        List<CartDto> list = new ArrayList<>();
        productsInCart.forEach((key, value) -> list.add(new CartDto(key.getId(), key.getTitle(),
                key.getCost() * value, value)));
        return list;
    }

    public void deleteFromCart(ProductDto product) {
        if (productsInCart.containsKey(product)) {
            if (productsInCart.get(product) > 1) {
                productsInCart.put(product, productsInCart.get(product) - 1);
            } else {
                productsInCart.remove(product);
            }
        }
    }


}
