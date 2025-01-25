package com.hahn.software.employeerecordsmanagementbackend.security.service;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;

public interface IAuthService {
    String login(LoginRequest request);
}
