package com.hahn.software.employeerecordsmanagementbackend.security.service.impl;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginResponse;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.User;
import com.hahn.software.employeerecordsmanagementbackend.security.repository.UserRepository;
import com.hahn.software.employeerecordsmanagementbackend.security.service.IAuthService;
import com.hahn.software.employeerecordsmanagementbackend.security.utils.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service @AllArgsConstructor @Transactional
public class IAuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationUser = userMapper.mapLoginRequestToAuthenticationUser(request);
        authenticationManager.authenticate(authenticationUser);

        User authenticatedUser = userRepository.getUserByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
