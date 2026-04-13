package com.cashify.cashify_backend.controller;

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
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
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
