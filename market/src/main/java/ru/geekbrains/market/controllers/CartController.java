package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.bins.Cart;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final Cart cart;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(productService.findProductById(id).get());
    }

    @GetMapping
    public List<CartDto> getCart() {
        return cart.getCart();
    }

    @GetMapping("/delete/{id}")
    public void deleteFromCart(@PathVariable Long id) {
        cart.deleteFromCart(productService.findProductById(id).get());
    }

}
