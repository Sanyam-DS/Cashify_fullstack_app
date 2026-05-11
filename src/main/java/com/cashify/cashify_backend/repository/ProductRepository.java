package com.cashify.cashify_backend.repository;

import com.cashify.cashify_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByBrandIgnoreCase(String brand);
    List<Product> findByPriceLessThanEqual(double price);
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findByBrandIgnoreCaseAndCategoryIgnoreCaseAndPriceLessThanEqual(
            String brand,
            String category,
            double price
    );
}
