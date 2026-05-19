package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.dto.LoginRequestDTO;
import com.cashify.cashify_backend.dto.LoginResponseDTO;
import com.cashify.cashify_backend.dto.RegisterRequestDTO;
import com.cashify.cashify_backend.dto.RegisterResponseDTO;
import com.cashify.cashify_backend.entity.User;
import com.cashify.cashify_backend.response.ApiResponse;
import com.cashify.cashify_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(request.getPassword());

        user.setRole(request.getRole());

        User savedUser = userService.register(user);

        RegisterResponseDTO response =
                new RegisterResponseDTO(
                        savedUser.getId(),
                        savedUser.getName(),
                        savedUser.getEmail(),
                        savedUser.getRole()
                );

        return new ApiResponse<>(
                true,
                "User registered successfully",
                response
        );
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request
    ) {

        String token = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        LoginResponseDTO response =
                new LoginResponseDTO(token);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }

    @GetMapping("/profile")
    public String profile() {
        return "Protected data";
    }

    @GetMapping("/admin/test")
    public String admin() {
        return "Admin access only";
    }

    @GetMapping("/user/test")
    public String userTest() {
        return "User access";
    }
}
