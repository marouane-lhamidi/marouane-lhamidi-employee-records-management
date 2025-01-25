package com.hahn.software.employeerecordsmanagementbackend.security.service.impl;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class IAuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // If successful, generate and return token (e.g., JWT)
        return "Authenticated successfully. [JWT placeholder]";
    }
}
