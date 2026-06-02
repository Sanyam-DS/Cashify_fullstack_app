package com.cashify.cashify_backend.dto;

import com.cashify.cashify_backend.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusUpdateDTO {

    private OrderStatus status;
}
