package com.cashify.cashify_backend.repository;

import com.cashify.cashify_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);

    @Query("""
       SELECT COALESCE(SUM(o.totalAmount),0)
       FROM OrderEntity o
       """)
    Double getTotalRevenue();

    @Query(value = """
    SELECT
    DATE_FORMAT(order_date, '%Y-%m') AS month,
    SUM(total_amount) AS revenue
    FROM order_entity
    GROUP BY DATE_FORMAT(order_date, '%Y-%m')
    ORDER BY month
    """, nativeQuery = true)
    List<Object[]> getMonthlyRevenue();
}

