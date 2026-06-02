package com.cashify.cashify_backend.entity;

import com.cashify.cashify_backend.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)

    @JsonIgnoreProperties({
            "password",
            "email",
            "role"
    })
    private User user;

    @OneToMany(mappedBy = "orderEntity",
            cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
