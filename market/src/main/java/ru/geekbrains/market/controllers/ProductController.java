package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // http://localhost:8189/market/products?min_cost=30&max_cost=400
    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "0", name = "min_cost") Double minCost,
                                           @RequestParam(required = false, name = "max_cost") Double maxCost,
                                           @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productService.getAllProducts(minCost, maxCost, page);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return productService.saveOrUpdate(product);
    }

    // @RequestParam Long id, @RequestParam String title, @RequestParam double cost
    // http://localhost:8189/app/products?id=21&title=abricot&cost=100
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto newProduct) {
        return productService.saveOrUpdate(newProduct);
    }

}
