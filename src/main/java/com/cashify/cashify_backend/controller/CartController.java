package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.entity.CartItem;
import com.cashify.cashify_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {

        return cartService.addToCart(
                userId,
                productId,
                quantity
        );
    }

    @GetMapping("/user/{userId}")
    public List<CartItem> getUserCart(
            @PathVariable Long userId
    ) {

        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public String removeFromCart(
            @PathVariable Long cartItemId
    ) {

        return cartService.removeFromCart(cartItemId);
    }
}
