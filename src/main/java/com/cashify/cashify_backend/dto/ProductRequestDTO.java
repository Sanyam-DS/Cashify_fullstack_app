package com.cashify.cashify_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Price must be positive")
    private double price;

    private String imageUrl;
}
