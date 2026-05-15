package com.cashify.cashify_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;

    private LocalDateTime orderDate;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)

    @JsonIgnoreProperties({
            "password",
            "email",
            "role"
    })
    private User user;
}
