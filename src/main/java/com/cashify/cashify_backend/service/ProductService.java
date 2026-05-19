package com.cashify.cashify_backend.service;

import com.cashify.cashify_backend.dto.ProductDetailsDTO;
import com.cashify.cashify_backend.dto.ProductResponseDTO;
import com.cashify.cashify_backend.entity.Product;
import com.cashify.cashify_backend.exception.ProductNotFoundException;
import com.cashify.cashify_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products =
                productRepository.findAll();

        return products.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ProductDetailsDTO updateProduct(
            Long id,
            Product updatedProduct
    ) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"
                        ));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());

        Product savedProduct =
                productRepository.save(existingProduct);

        return new ProductDetailsDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getBrand(),
                savedProduct.getCategory(),
                savedProduct.getPrice(),
                savedProduct.getImageUrl()
        );
    }

    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);

        return "Product deleted successfully";
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand);
    }

    public List<Product> getProductsByPrice(double price) {

        return productRepository.findByPriceLessThanEqual(price);
    }

    public List<Product> getProductsByCategory(String category) {

        return productRepository.findByCategoryIgnoreCase(category);
    }

    public Page<Product> getProducts(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return productRepository.findAll(pageable);
    }

    public List<Product> filterProducts(
            String brand,
            String category,
            double price
    ) {

        return productRepository
                .findByBrandIgnoreCaseAndCategoryIgnoreCaseAndPriceLessThanEqual(
                        brand,
                        category,
                        price
                );
    }

    private ProductResponseDTO mapToDTO(Product product) {

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public ProductDetailsDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        return new ProductDetailsDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getCategory(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}
