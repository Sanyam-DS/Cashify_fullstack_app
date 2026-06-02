package com.cashify.cashify_backend.service;

import com.cashify.cashify_backend.dto.OrderHistoryDTO;
import com.cashify.cashify_backend.dto.OrderItemDTO;
import com.cashify.cashify_backend.entity.*;
import com.cashify.cashify_backend.enums.OrderStatus;
import com.cashify.cashify_backend.exception.InvalidOrderStatusException;
import com.cashify.cashify_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderEntity placeOrder(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems =
                cartItemRepository.findByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = 0;

        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice()
                    * item.getQuantity();
        }

        OrderEntity order = new OrderEntity();

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        order.setTotalAmount(total);

        OrderEntity savedOrder =
                orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrderEntity(savedOrder);

            orderItem.setProduct(cartItem.getProduct());

            orderItem.setQuantity(cartItem.getQuantity());

            orderItem.setPrice(
                    cartItem.getProduct().getPrice()
            );

            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteByUserId(user.getId());

        return savedOrder;
    }

    public List<OrderEntity> getUserOrders(Long userId) {

        return orderRepository.findByUserId(userId);
    }

    public List<OrderHistoryDTO> getOrderHistory(
            String email
    ){

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        List<OrderEntity> orders =
                orderRepository.findByUserId(
                        user.getId()
                );

        return orders.stream()
                .map(order -> {
                    List<OrderItemDTO> items =
                            order.getOrderItems()
                                    .stream()
                                    .map(item ->
                                            new OrderItemDTO(
                                                    item.getProduct().getName(),
                                                    item.getQuantity(),
                                                    item.getPrice()
                                            )
                                    )
                                    .toList();
                    return new OrderHistoryDTO(
                            order.getId(),
                            order.getStatus().toString(),
                            order.getOrderDate(),
                            order.getTotalAmount(),
                            items
                    );
                })
                .toList();
    }

    public OrderEntity updateOrderStatus(
            Long orderId, OrderStatus newStatus
    ){
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(()->
                        new RuntimeException("Order not found"));

        OrderStatus currentStatus = order.getStatus();

        if(!isValidTransition(
                currentStatus,
                newStatus
        )){
            throw new InvalidOrderStatusException(
                    "Invalid status transition from"
                    +currentStatus
                    +"to"
                    +newStatus
            );
        }

        order.setStatus(newStatus);

        return orderRepository.save(order);
    }

    private boolean isValidTransition(
            OrderStatus current,
            OrderStatus next
    ){

        switch(current) {
            case PLACED:
                return next == OrderStatus.CONFIRMED || next == OrderStatus.CANCELLED;

            case CONFIRMED:
                return next == OrderStatus.SHIPPED || next == OrderStatus.CANCELLED;

            case SHIPPED:
                return next == OrderStatus.OUT_FOR_DELIVERY;

            case OUT_FOR_DELIVERY:
                return next == OrderStatus.DELIVERED;

            case DELIVERED:
            case CANCELLED:
                return false;

            default:
                return false;
        }

    }
}
