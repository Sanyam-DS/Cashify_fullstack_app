package com.cashify.cashify_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;
}
