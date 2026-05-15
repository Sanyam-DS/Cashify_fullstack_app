package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.dto.ProductDetailsDTO;
import com.cashify.cashify_backend.dto.ProductResponseDTO;
import com.cashify.cashify_backend.entity.Product;
import com.cashify.cashify_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDetailsDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {

        return productService.searchProducts(name);
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {

        return productService.getProductsByBrand(brand);
    }

    @GetMapping("/price/{price}")
    public List<Product> getProductsByPrice(@PathVariable double price) {

        return productService.getProductsByPrice(price);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {

        return productService.getProductsByCategory(category);
    }

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {

        return productService.getProducts(page, size, sortBy);
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam double price
    ) {

        return productService.filterProducts(
                brand,
                category,
                price
        );
    }
}
