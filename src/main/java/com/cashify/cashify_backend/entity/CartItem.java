package com.cashify.cashify_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({
            "password",
            "email",
            "role"
    })
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
