package com.hahn.software.employeerecordsmanagementbackend.dto;

import com.hahn.software.employeerecordsmanagementbackend.dto.enums.EmploymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Employee",
        description = "Schema to hold error Employee information"
)
public class EmployeeDto {
    @Schema(description = "The unique identifier for the employee Not Obligatory when creating and updating must be unique", example = "550e8400-e29b-41d4-a716-446655440000")
    private String employeeId;
    @Schema(description = "The full name of the employee", example = "John Doe")
    @NotEmpty(message = "Full name cannot be empty or null")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;

    @Schema(description = "The job title of the employee", example = "Software Engineer")
    @Size(max = 50, message = "Job title cannot exceed 50 characters")
    private String jobTitle;

    @Schema(description = "The date the employee was hired", example = "2023-01-15")
    @NotNull(message = "Hire date cannot be null")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private Date hireDate;

    @Schema(description = "The email address of the employee", example = "john.doe@example.com")
    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Email must be valid")
    private String email;

    @Schema(description = "The phone number of the employee", example = "+1234567890")
    @Pattern(regexp = "\\+?[0-9]{7,20}", message = "Phone number must be valid and between 7 to 20 digits")
    private String phoneNumber;

    @Schema(description = "The address of the employee", example = "123 Main Street, Springfield")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @Schema(description = "The employment status of the employee ACTIVE, INACTIVE", example = "ACTIVE")
    @NotNull(message = "Employment status cannot be null")
    private EmploymentStatus employmentStatus;

    @Schema(description = "The Name of the department the employee belongs to", example = "IT")
    @NotNull(message = "Department Name cannot be null")
    private String departmentName;
}
