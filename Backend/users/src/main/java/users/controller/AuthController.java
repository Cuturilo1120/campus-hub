package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import users.model.dto.AuthResponse;
import users.model.dto.LoginRequest;
import users.model.dto.RegisterRequest;
import users.model.dto.StudentRegisterRequest;
import users.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @PostMapping("/students/register")
    @PreAuthorize("hasAnyRole('CASHIER', 'PRINCIPAL')")
    public ResponseEntity<Map<String, String>> registerStudent(@RequestBody StudentRegisterRequest request) {
        authService.registerStudent(request);
        return ResponseEntity.ok(Map.of("message", "Student registered successfully"));
    }

    @PostMapping("/students/login")
    public ResponseEntity<AuthResponse> loginStudent(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginStudent(request));
    }
}
