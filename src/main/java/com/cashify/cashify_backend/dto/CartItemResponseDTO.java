package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CartItemResponseDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
}
