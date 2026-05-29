package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.dto.OrderHistoryDTO;
import com.cashify.cashify_backend.entity.OrderEntity;
import com.cashify.cashify_backend.response.ApiResponse;
import com.cashify.cashify_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ApiResponse<OrderEntity> placeOrder(
            Authentication authentication
    ) {

        String email = authentication.getName();

        OrderEntity order = orderService.placeOrder(email);

        return new ApiResponse<> (
            true,
            "Order placed Successfully",
            order
        );
    }

    @GetMapping("/user/{userId}")
    public List<OrderEntity> getUserOrders(
            @PathVariable Long userId
    ) {

        return orderService.getUserOrders(userId);
    }

    @GetMapping("/history")
    public ApiResponse<List<OrderHistoryDTO>>
    getOrderHistory(
            Authentication authentication
    ) {

        String email =
                authentication.getName();

        List<OrderHistoryDTO> orders =
                orderService.getOrderHistory(email);

        return new ApiResponse<>(
                true,
                "Order history fetched successfully",
                orders
        );
    }
}
