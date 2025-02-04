package com.hahn.software.employeerecordsmanagementbackend.controller;

import com.hahn.software.employeerecordsmanagementbackend.constants.EmployeeRecordConstants;
import com.hahn.software.employeerecordsmanagementbackend.dto.EmployeeDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.ErrorResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.ResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD APIs for Employees",
        description = "CRUD REST APIs to create, fetch, update, and delete employees"
)
@RestController
@RequestMapping(path = "/api/employees")
@AllArgsConstructor
@Validated
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Operation(
            summary = "Create Employee REST API",
            description = "REST API to create a new employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        employeeService.createEmployee(employeeDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode(EmployeeRecordConstants.STATUS_201)
                        .statusMessage(EmployeeRecordConstants.MESSAGE_201)
                        .build());
    }

    @Operation(
            summary = "Fetch Employee REST API",
            description = "REST API to fetch employee details by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<EmployeeDto> fetchEmployee(@RequestParam String employeeId) {
        EmployeeDto employeeDto = employeeService.fetchEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @Operation(
            summary = "Fetch All Employees REST API",
            description = "REST API to fetch all employees details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK - Successfully fetched all employees"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchAll")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<EmployeeDto>> fetchAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.fetchAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtos);
    }
    @Operation(
            summary = "Update Employee REST API",
            description = "REST API to update employee details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('UPDATE', 'LIMITED_UPDATE')")
    public ResponseEntity<ResponseDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        boolean isUpdated = employeeService.updateEmployee(employeeDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_200).statusMessage(EmployeeRecordConstants.MESSAGE_200).build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_417).statusMessage(EmployeeRecordConstants.MESSAGE_417_UPDATE).build());
        }
    }

    @Operation(
            summary = "Delete Employee REST API",
            description = "REST API to delete an employee by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestParam String employeeId) {
        boolean isDeleted = employeeService.deleteEmployee(employeeId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_200).statusMessage(EmployeeRecordConstants.MESSAGE_200).build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_417).statusMessage(EmployeeRecordConstants.MESSAGE_417_UPDATE).build());
        }
    }
}
