package com.cashify.cashify_backend.repository;

import com.cashify.cashify_backend.dto.TopProductDTO;
import com.cashify.cashify_backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

    @Query("""
    SELECT new com.cashify.cashify_backend.dto.TopProductDTO(
        oi.product.id,
        oi.product.name,
        SUM(oi.quantity)
    )
    FROM OrderItem oi
    GROUP BY oi.product.id, oi.product.name
    ORDER BY SUM(oi.quantity) DESC
    """)
    List<TopProductDTO> getTopSellingProducts();
}
