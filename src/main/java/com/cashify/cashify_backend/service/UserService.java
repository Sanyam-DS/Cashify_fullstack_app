package com.cashify.cashify_backend.service;

import com.cashify.cashify_backend.entity.User;
import com.cashify.cashify_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cashify.cashify_backend.config.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Password encoder
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Register user
    public User register(User user) {
        // Encrypt password before saving
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Login user
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}
