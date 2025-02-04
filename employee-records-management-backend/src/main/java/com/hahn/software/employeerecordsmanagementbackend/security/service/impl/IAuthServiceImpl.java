package com.hahn.software.employeerecordsmanagementbackend.security.service.impl;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginResponse;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.User;
import com.hahn.software.employeerecordsmanagementbackend.security.repository.UserRepository;
import com.hahn.software.employeerecordsmanagementbackend.security.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class IAuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request
                                .getPassword()
                )
        );
        User authenticatedUser = userRepository.getUserByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
