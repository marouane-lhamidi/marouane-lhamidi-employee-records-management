package com.hahn.software.employeerecordsmanagementbackend.controller;

import com.hahn.software.employeerecordsmanagementbackend.constants.EmployeeRecordConstants;
import com.hahn.software.employeerecordsmanagementbackend.dto.EmployeeDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.ResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.service.IEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private IEmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        doNothing().when(employeeService).createEmployee(employeeDto);

        // Act
        ResponseEntity<ResponseDto> response = employeeController.createEmployee(employeeDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(EmployeeRecordConstants.STATUS_201, response.getBody().getStatusCode());
        verify(employeeService, times(1)).createEmployee(employeeDto);
    }

    @Test
    void testFetchEmployee() {
        // Arrange
        String employeeId = "123";
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.fetchEmployee(employeeId)).thenReturn(employeeDto);

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.fetchEmployee(employeeId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employeeDto, response.getBody());
        verify(employeeService, times(1)).fetchEmployee(employeeId);
    }

    @Test
    void testFetchAllEmployees() {
        // Arrange
        EmployeeDto employee1 = new EmployeeDto();
        EmployeeDto employee2 = new EmployeeDto();
        List<EmployeeDto> employees = Arrays.asList(employee1, employee2);
        when(employeeService.fetchAllEmployees()).thenReturn(employees);

        // Act
        ResponseEntity<List<EmployeeDto>> response = employeeController.fetchAllEmployees();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employees, response.getBody());
        verify(employeeService, times(1)).fetchAllEmployees();
    }

    @Test
    void testUpdateEmployee_Success() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.updateEmployee(employeeDto)).thenReturn(true);

        // Act
        ResponseEntity<ResponseDto> response = employeeController.updateEmployee(employeeDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(EmployeeRecordConstants.STATUS_200, response.getBody().getStatusCode());
        verify(employeeService, times(1)).updateEmployee(employeeDto);
    }

    @Test
    void testUpdateEmployee_Failure() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.updateEmployee(employeeDto)).thenReturn(false);

        // Act
        ResponseEntity<ResponseDto> response = employeeController.updateEmployee(employeeDto);

        // Assert
        assertEquals(417, response.getStatusCodeValue());
        assertEquals(EmployeeRecordConstants.STATUS_417, response.getBody().getStatusCode());
        verify(employeeService, times(1)).updateEmployee(employeeDto);
    }

    @Test
    void testDeleteEmployee_Success() {
        // Arrange
        String employeeId = "123";
        when(employeeService.deleteEmployee(employeeId)).thenReturn(true);

        // Act
        ResponseEntity<ResponseDto> response = employeeController.deleteEmployee(employeeId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(EmployeeRecordConstants.STATUS_200, response.getBody().getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }

    @Test
    void testDeleteEmployee_Failure() {
        // Arrange
        String employeeId = "123";
        when(employeeService.deleteEmployee(employeeId)).thenReturn(false);

        // Act
        ResponseEntity<ResponseDto> response = employeeController.deleteEmployee(employeeId);

        // Assert
        assertEquals(417, response.getStatusCodeValue());
        assertEquals(EmployeeRecordConstants.STATUS_417, response.getBody().getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }
}
