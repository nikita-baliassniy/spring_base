package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// root: http://localhost:8189/app/products

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    // public String saveNewProduct(@RequestParam Long id, @RequestParam String title, @RequestParam double cost) {
    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product newProduct) {
        productsService.saveOrUpdateProduct(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct) {
        productsService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products/";
    }

}
