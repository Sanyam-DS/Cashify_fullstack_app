package com.cashify.cashify_backend.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;

    private String password;
}
