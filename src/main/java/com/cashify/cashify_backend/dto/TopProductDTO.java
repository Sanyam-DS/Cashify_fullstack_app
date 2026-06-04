package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopProductDTO {

    private long productId;
    private String productName;
    private long totalSold;
}
