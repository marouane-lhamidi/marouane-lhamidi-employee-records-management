package com.hahn.software.employeerecordsmanagementbackend.security.controller;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginResponse;
import com.hahn.software.employeerecordsmanagementbackend.security.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") @AllArgsConstructor
public class SecurityController {
    private final IAuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse user = authService.login(request);
        return ResponseEntity.ok(user);
    }
}
