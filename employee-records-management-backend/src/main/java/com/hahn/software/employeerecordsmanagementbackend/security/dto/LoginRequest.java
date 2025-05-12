package com.hahn.software.employeerecordsmanagementbackend.security.dto;

import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class LoginRequest {
    private String username;
    private String password;
}
