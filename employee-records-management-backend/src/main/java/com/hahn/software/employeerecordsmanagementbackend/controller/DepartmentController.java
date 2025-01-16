package com.hahn.software.employeerecordsmanagementbackend.controller;

import com.hahn.software.employeerecordsmanagementbackend.constants.EmployeeRecordConstants;
import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.ErrorResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.ResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.service.IDepartmentService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD APIs for Departments",
        description = "CRUD REST APIs to create, fetch, update, and delete departments"
)
@RestController
@RequestMapping(path = "/api/departments")
@AllArgsConstructor
@Validated
public class DepartmentController {

    private final IDepartmentService departmentService;

    @Operation(
            summary = "Create Department REST API",
            description = "REST API to create a new department"
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
    public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        departmentService.createDepartment(departmentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode(EmployeeRecordConstants.STATUS_201)
                        .statusMessage(EmployeeRecordConstants.MESSAGE_201)
                        .build());
    }

    @Operation(
            summary = "Fetch Department REST API",
            description = "REST API to fetch department details by Department Name"
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
    public ResponseEntity<DepartmentDto> fetchDepartment(@RequestParam String departmentName) {
        DepartmentDto departmentDto = departmentService.fetchDepartment(departmentName);
        return ResponseEntity.status(HttpStatus.OK).body(departmentDto);
    }

    @Operation(
            summary = "Fetch All Departments REST API",
            description = "REST API to fetch details of all departments"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
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
    public ResponseEntity<List<DepartmentDto>> fetchAllDepartments() {
        List<DepartmentDto> departments = departmentService.fetchAllDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    @Operation(
            summary = "Update Department REST API",
            description = "REST API to update department details"
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
    public ResponseEntity<ResponseDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        boolean isUpdated = departmentService.updateDepartment(departmentDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_200).statusMessage(EmployeeRecordConstants.MESSAGE_200).build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_417).statusMessage(EmployeeRecordConstants.MESSAGE_417_UPDATE).build());
        }
    }

    @Operation(
            summary = "Delete Department REST API",
            description = "REST API to delete a department by Department Name"
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
    public ResponseEntity<ResponseDto> deleteDepartment(@RequestParam String departmentName) {
        boolean isDeleted = departmentService.deleteDepartment(departmentName);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_200).statusMessage( EmployeeRecordConstants.MESSAGE_200).build());}else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder().statusCode(EmployeeRecordConstants.STATUS_417).statusMessage( EmployeeRecordConstants.MESSAGE_417_UPDATE).build());
        }
    }
}
