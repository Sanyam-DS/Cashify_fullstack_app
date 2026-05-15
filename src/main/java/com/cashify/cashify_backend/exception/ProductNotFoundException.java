package com.cashify.cashify_backend.exception;

public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException(String message) {

        super(message);
    }
}
