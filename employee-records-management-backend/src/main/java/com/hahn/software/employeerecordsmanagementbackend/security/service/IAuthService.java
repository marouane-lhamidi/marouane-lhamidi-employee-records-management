package com.hahn.software.employeerecordsmanagementbackend.security.service;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest request);
}
