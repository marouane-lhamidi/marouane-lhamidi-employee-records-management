package com.hahn.software.employeerecordsmanagementbackend.security.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
@Getter @Setter
public class UserRegisterDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email @NotBlank
    private String email;

    @NotBlank @Size(min = 6, max = 100)
    private String password;

    @NotEmpty
    private List<String> roles;
    @NotEmpty
    private boolean isEnabled;
    @NotEmpty
    private boolean isTemporaryPassword;
    @NotEmpty
    private Integer expirationDuration;
}