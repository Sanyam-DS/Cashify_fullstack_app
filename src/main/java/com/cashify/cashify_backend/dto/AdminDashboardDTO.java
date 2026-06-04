package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDashboardDTO {

    private long totalUsers;
    private long totalProducts;
    private long totalOrders;
    private double totalRevenue;
}
