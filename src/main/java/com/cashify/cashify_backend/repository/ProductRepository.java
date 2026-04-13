package com.cashify.cashify_backend.repository;

import com.cashify.cashify_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
