package com.hahn.software.employeerecordsmanagementbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @Builder
@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(description = "The api path")
    private String apiPath;
    @Schema(description = "The error code")
    private HttpStatus errorCode;
    @Schema(description = "The error message")
    private String errorMessage;
    @Schema(description = "The error time", example = "2020-01-01T12:00:00")
    private LocalDateTime errorTime;
}
