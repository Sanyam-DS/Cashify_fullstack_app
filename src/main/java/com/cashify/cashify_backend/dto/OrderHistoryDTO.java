package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderHistoryDTO {

    private Long id;
    private String status;
    private LocalDateTime orderDate;
    private double totalAmount;
    private List<OrderItemDTO> items;
}
