package com.cashify.cashify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String role;
}
