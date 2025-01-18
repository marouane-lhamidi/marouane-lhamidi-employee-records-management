package com.hahn.software.employeerecordsmanagementbackend.controller;

import com.hahn.software.employeerecordsmanagementbackend.constants.EmployeeRecordConstants;
import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.ResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.service.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDepartment() {
        DepartmentDto departmentDto = new DepartmentDto();
        doNothing().when(departmentService).createDepartment(departmentDto);

        ResponseEntity<ResponseDto> response = departmentController.createDepartment(departmentDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(EmployeeRecordConstants.STATUS_201, response.getBody().getStatusCode());
        assertEquals(EmployeeRecordConstants.MESSAGE_201, response.getBody().getStatusMessage());
        verify(departmentService, times(1)).createDepartment(departmentDto);
    }

    @Test
    void testFetchDepartment() {
        String departmentName = "HR";
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(departmentName);

        when(departmentService.fetchDepartment(departmentName)).thenReturn(departmentDto);

        ResponseEntity<DepartmentDto> response = departmentController.fetchDepartment(departmentName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentName, response.getBody().getName());
        verify(departmentService, times(1)).fetchDepartment(departmentName);
    }

    @Test
    void testFetchAllDepartments() {
        DepartmentDto department1 = new DepartmentDto();
        department1.setName("HR");

        DepartmentDto department2 = new DepartmentDto();
        department2.setName("Finance");

        List<DepartmentDto> departments = Arrays.asList(department1, department2);
        when(departmentService.fetchAllDepartments()).thenReturn(departments);

        ResponseEntity<List<DepartmentDto>> response = departmentController.fetchAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(departmentService, times(1)).fetchAllDepartments();
    }

    @Test
    void testUpdateDepartmentSuccess() {
        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentService.updateDepartment(departmentDto)).thenReturn(true);

        ResponseEntity<ResponseDto> response = departmentController.updateDepartment(departmentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(EmployeeRecordConstants.STATUS_200, response.getBody().getStatusCode());
        assertEquals(EmployeeRecordConstants.MESSAGE_200, response.getBody().getStatusMessage());
        verify(departmentService, times(1)).updateDepartment(departmentDto);
    }

    @Test
    void testUpdateDepartmentFailure() {
        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentService.updateDepartment(departmentDto)).thenReturn(false);

        ResponseEntity<ResponseDto> response = departmentController.updateDepartment(departmentDto);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals(EmployeeRecordConstants.STATUS_417, response.getBody().getStatusCode());
        assertEquals(EmployeeRecordConstants.MESSAGE_417_UPDATE, response.getBody().getStatusMessage());
        verify(departmentService, times(1)).updateDepartment(departmentDto);
    }

    @Test
    void testDeleteDepartmentSuccess() {
        String departmentName = "HR";
        when(departmentService.deleteDepartment(departmentName)).thenReturn(true);

        ResponseEntity<ResponseDto> response = departmentController.deleteDepartment(departmentName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(EmployeeRecordConstants.STATUS_200, response.getBody().getStatusCode());
        assertEquals(EmployeeRecordConstants.MESSAGE_200, response.getBody().getStatusMessage());
        verify(departmentService, times(1)).deleteDepartment(departmentName);
    }

    @Test
    void testDeleteDepartmentFailure() {
        String departmentName = "HR";
        when(departmentService.deleteDepartment(departmentName)).thenReturn(false);

        ResponseEntity<ResponseDto> response = departmentController.deleteDepartment(departmentName);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals(EmployeeRecordConstants.STATUS_417, response.getBody().getStatusCode());
        assertEquals(EmployeeRecordConstants.MESSAGE_417_UPDATE, response.getBody().getStatusMessage());
        verify(departmentService, times(1)).deleteDepartment(departmentName);
    }
}
