package com.example.auth.controller;

import com.example.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void register(@RequestBody Map<String, String> req) {
        service.register(req.get("username"), req.get("password"));
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req,
                                     HttpSession session) {
        boolean ok = service.authenticate(req.get("username"), req.get("password"));
        if (!ok) throw new RuntimeException("Invalid credentials");

        session.setAttribute("user", req.get("username"));
        return Map.of("username", req.get("username"));
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/me")
    public Map<String, Object> me(HttpSession session) {
        String user = (String) session.getAttribute("user");

        if (user == null) {
            return Map.of(); // empty JSON {}
        }

        return Map.of("username", user);
    }

}
