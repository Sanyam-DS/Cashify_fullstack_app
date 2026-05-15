package com.cashify.cashify_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
