package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String brand;

    private String category;

    private double price;

    private String imageUrl;
}
