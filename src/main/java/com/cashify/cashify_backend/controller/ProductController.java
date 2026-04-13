package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.entity.Product;
import com.cashify.cashify_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
