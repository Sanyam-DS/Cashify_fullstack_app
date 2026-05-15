package com.cashify.cashify_backend.repository;

import com.cashify.cashify_backend.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    @Transactional
    @Modifying
    void deleteByUserId(Long userId);
}
