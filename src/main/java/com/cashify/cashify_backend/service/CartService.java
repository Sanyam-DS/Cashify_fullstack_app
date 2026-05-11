package com.cashify.cashify_backend.service;

import com.cashify.cashify_backend.entity.CartItem;
import com.cashify.cashify_backend.entity.Product;
import com.cashify.cashify_backend.entity.User;
import com.cashify.cashify_backend.repository.CartItemRepository;
import com.cashify.cashify_backend.repository.ProductRepository;
import com.cashify.cashify_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItem addToCart(Long userId,
                              Long productId,
                              int quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getUserCart(Long userId) {

        return cartItemRepository.findByUserId(userId);
    }

    public String removeFromCart(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(cartItem);

        return "Item removed from cart";
    }
}
