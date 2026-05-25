package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.dto.CartItemResponseDTO;
import com.cashify.cashify_backend.entity.CartItem;
import com.cashify.cashify_backend.response.ApiResponse;
import com.cashify.cashify_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ApiResponse<CartItem> addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            Authentication authentication
    ) {

        String email = authentication.getName();

        CartItem cartItems =  cartService.addToCart(
                email,
                productId,
                quantity
        );

        return new ApiResponse<> (
            true,
            "Product added to cart",
            cartItems
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

    @GetMapping
    public ApiResponse<List<CartItemResponseDTO>> getCartItems(
            Authentication authentication
    ){
        String email = authentication.getName();
        List<CartItemResponseDTO> cartItems = cartService.getCartItems(email);

        return new ApiResponse<>(
                true,
                "Cart Fetch Successfull",
                cartItems
        );
    }
}
