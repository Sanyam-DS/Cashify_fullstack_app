package com.cashify.cashify_backend.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String name;

    private String email;

    private String password;

    private String role;
}
