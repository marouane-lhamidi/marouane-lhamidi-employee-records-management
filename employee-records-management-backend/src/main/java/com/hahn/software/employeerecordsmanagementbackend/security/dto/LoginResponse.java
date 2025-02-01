package com.hahn.software.employeerecordsmanagementbackend.security.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class LoginResponse {
    private String token;
    private long expiresIn;
}
