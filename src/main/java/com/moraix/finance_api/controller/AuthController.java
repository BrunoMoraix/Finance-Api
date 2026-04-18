package com.moraix.finance_api.controller;

import com.moraix.finance_api.config.JwtUtil;
import com.moraix.finance_api.model.User;
import com.moraix.finance_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        User user = userService.createUser(
                body.get("name"),
                body.get("email"),
                body.get("password")
        );
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        User user = userService.findByEmail(body.get("email"));
        if (!passwordEncoder.matches(body.get("password"), user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
}