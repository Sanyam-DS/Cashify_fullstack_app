package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.dto.ProductDetailsDTO;
import com.cashify.cashify_backend.dto.ProductResponseDTO;
import com.cashify.cashify_backend.entity.Product;
import com.cashify.cashify_backend.response.ApiResponse;
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
    public ApiResponse<ProductDetailsDTO> addProduct(
            @RequestBody Product product
    ) {

        ProductDetailsDTO savedProduct =
                productService.addProduct(product);

        return new ApiResponse<>(
                true,
                "Product added successfully",
                savedProduct
        );
    }

    @GetMapping("/all")
    public ApiResponse<List<ProductResponseDTO>>
    getAllProducts() {

        List<ProductResponseDTO> products =
                productService.getAllProducts();

        return new ApiResponse<>(
                true,
                "Products fetched successfully",
                products
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailsDTO>
    getProductById(
            @PathVariable Long id
    ) {

        ProductDetailsDTO product =
                productService.getProductById(id);

        return new ApiResponse<>(
                true,
                "Product fetched successfully",
                product
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse<ProductDetailsDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {

        ProductDetailsDTO updatedProduct =
                productService.updateProduct(id, product);

        return new ApiResponse<>(
                true,
                "Product updated successfully",
                updatedProduct
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteProduct(
            @PathVariable Long id
    ) {

        String message =
                productService.deleteProduct(id);

        return new ApiResponse<>(
                true,
                message,
                null
        );
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
