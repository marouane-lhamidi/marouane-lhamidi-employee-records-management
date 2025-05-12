package com.hahn.software.employeerecordsmanagementbackend.security.controller;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginResponse;
import com.hahn.software.employeerecordsmanagementbackend.security.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") @AllArgsConstructor
public class SecurityController {
    private final IAuthService authService;
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse user = authService.login(request);
        return ResponseEntity.ok(user);
    }
//    @PostMapping("/auth/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody TokenRequest request) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/auth/logout")
//    public ResponseEntity<?> logout() {
//        return ResponseEntity.ok().build();
//    }
//

}
