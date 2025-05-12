package com.hahn.software.employeerecordsmanagementbackend.security.dto;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDto {
    private String username;
    private String email;
    private boolean enabled;
    private Timestamp expirationDate;
    private Timestamp passwordExpirationDate;
    private boolean isTemporaryPassword;
    private List<String> roles;
}
