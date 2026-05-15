package com.cashify.cashify_backend.controller;

import com.cashify.cashify_backend.dto.LoginRequestDTO;
import com.cashify.cashify_backend.dto.LoginResponseDTO;
import com.cashify.cashify_backend.dto.RegisterRequestDTO;
import com.cashify.cashify_backend.dto.RegisterResponseDTO;
import com.cashify.cashify_backend.entity.User;
import com.cashify.cashify_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RegisterResponseDTO register(
            @RequestBody RegisterRequestDTO request
    ) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(request.getPassword());

        user.setRole(request.getRole());

        User savedUser = userService.register(user);

        return new RegisterResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request
    ) {

        String token = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new LoginResponseDTO(token);
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
