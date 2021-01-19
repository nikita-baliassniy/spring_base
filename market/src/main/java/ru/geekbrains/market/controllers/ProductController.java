package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // http://localhost:8189/app/products?min_cost=30&max_cost=400
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false, name = "min_cost") Double minCost,
                                        @RequestParam(required = false, name = "max_cost") Double maxCost) {
        return productService.getAllProducts(minCost, maxCost);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        System.out.println(id);
        productService.deleteById(id);
    }

    // @RequestParam Long id, @RequestParam String title, @RequestParam double cost
    // http://localhost:8189/app/products?id=21&title=abricot&cost=100
    @PostMapping
    public List<Product> saveNewProduct(@ModelAttribute Product newProduct) {
        productService.addProduct(newProduct);
        return productService.getAllProducts();
    }

}
