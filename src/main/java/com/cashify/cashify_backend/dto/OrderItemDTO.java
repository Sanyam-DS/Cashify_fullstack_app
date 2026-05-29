package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {

    private String productName;

    private int quantity;

    private double price;
}
