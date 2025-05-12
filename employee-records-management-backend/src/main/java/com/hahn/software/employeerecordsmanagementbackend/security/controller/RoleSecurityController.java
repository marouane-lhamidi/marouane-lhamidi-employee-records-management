package com.hahn.software.employeerecordsmanagementbackend.security.controller;

import com.hahn.software.employeerecordsmanagementbackend.security.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") @AllArgsConstructor
public class RoleSecurityController {
    private final IAuthService authService;
//    @GetMapping("/roles")
//    public ResponseEntity<?> getRoles() {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/roles")
//    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/roles/{id}")
//    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/roles/{id}")
//    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    /** PASSWORD MANAGEMENT **/
//    @PostMapping("/users/request-reset-password")
//    public ResponseEntity<?> requestPasswordReset(@RequestBody PasswordResetRequest request) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/users/reset-password")
//    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetDto resetDto) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/users/change-password")
//    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
//        return ResponseEntity.ok().build();
//    }
//
//    /** ADMIN DASHBOARD **/
//    @GetMapping("/admin/users")
//    public ResponseEntity<?> getAllUsers() {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/admin/users/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
}
