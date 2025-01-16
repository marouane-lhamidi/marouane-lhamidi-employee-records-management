package com.hahn.software.employeerecordsmanagementbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Department",
        description = "Schema to hold error Department information"
)
public class DepartmentDto {
    @Schema(description = "The ID of the department Not Obligatory when creating", example = "1")
    Long id;
    @Schema(description = "The name of the customer", example = "HR")
    @NotEmpty(message = "Name cannot be empty or null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    String name;
}
