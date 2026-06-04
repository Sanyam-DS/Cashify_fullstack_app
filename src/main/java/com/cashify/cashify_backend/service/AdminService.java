package com.cashify.cashify_backend.service;

import com.cashify.cashify_backend.dto.AdminDashboardDTO;
import com.cashify.cashify_backend.dto.TopProductDTO;
import com.cashify.cashify_backend.repository.OrderItemRepository;
import com.cashify.cashify_backend.repository.OrderRepository;
import com.cashify.cashify_backend.repository.ProductRepository;
import com.cashify.cashify_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public AdminDashboardDTO getDashboardStats(){

        long totalUsers = userRepository.count();
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();

        double totalRevenue =
                Optional.ofNullable(
                orderRepository.getTotalRevenue()
                ).orElse(0.0);

        return new AdminDashboardDTO(
                totalUsers,
                totalProducts,
                totalOrders,
                totalRevenue
        );
    }

    public List<TopProductDTO> getTopSellingProducts(){
        return orderItemRepository.getTopSellingProducts();
    }
}
