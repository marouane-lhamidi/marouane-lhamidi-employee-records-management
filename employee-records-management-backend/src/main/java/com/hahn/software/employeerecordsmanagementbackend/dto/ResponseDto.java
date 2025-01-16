package com.hahn.software.employeerecordsmanagementbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@Schema(
        name = "Response",
        description = "Schema to Response information"
)
public class ResponseDto {
    @Schema(description = "The api path")
    private String statusMessage;
    @Schema(description = "The status code")
    private String statusCode;
}
