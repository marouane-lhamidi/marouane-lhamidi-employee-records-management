package com.hahn.software.employeerecordsmanagementbackend.security.controller;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserRegisterDto;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.security.service.impl.IUserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users") @AllArgsConstructor
public class UserSecurityController {
    private final IUserServiceImpl userService;
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterDto userDto) {
        UserResponseDto userResponseDto = userService.registerUser(userDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/update/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String name, @Valid @RequestBody UserRegisterDto userDto) {
        UserResponseDto userResponseDto = userService.updateUser(name ,userDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/profiles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getUserProfiles() {
        List<UserResponseDto> userProfiles = userService.getAllUserProfiles();
        return ResponseEntity.ok(userProfiles);
    }

    @DeleteMapping("/users/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String name) {
        userService.deleteUser(name);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "User deleted successfully"));
    }


}
