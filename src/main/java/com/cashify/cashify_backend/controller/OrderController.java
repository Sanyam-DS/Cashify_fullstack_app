package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.entity.OrderEntity;
import com.cashify.cashify_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public OrderEntity placeOrder(
            @RequestParam Long userId
    ) {

        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<OrderEntity> getUserOrders(
            @PathVariable Long userId
    ) {

        return orderService.getUserOrders(userId);
    }
}
