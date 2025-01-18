package com.hahn.software.employeerecordsmanagementbackend.service;

import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import com.hahn.software.employeerecordsmanagementbackend.exception.DepartmentAlreadyExistsException;
import com.hahn.software.employeerecordsmanagementbackend.exception.ResourceNotFoundException;
import com.hahn.software.employeerecordsmanagementbackend.repository.DepartmentRepository;
import com.hahn.software.employeerecordsmanagementbackend.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createDepartment_Success() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "IT Department");
        Department department = Department.builder()
                .id(1L)
                .name("IT Department")
                .build();
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.empty());
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        assertDoesNotThrow(() -> departmentService.createDepartment(departmentDto));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void createDepartment_AlreadyExists() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "IT Department");
        Department department = Department.builder()
                .id(1L)
                .name("IT Department")
                .build();
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));

        assertThrows(DepartmentAlreadyExistsException.class, () -> departmentService.createDepartment(departmentDto));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    void fetchDepartment_Success() {
        String departmentName = "IT Department";
        Department department = Department.builder()
                .id(1L)
                .name(departmentName)
                .build();

        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));

        DepartmentDto result = departmentService.fetchDepartment(departmentName);

        assertNotNull(result);
        assertEquals(departmentName, result.getName());
    }

    @Test
    void fetchDepartment_NotFound() {
        String departmentName = "Non-Existent";

        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> departmentService.fetchDepartment(departmentName));
    }

    @Test
    void fetchAllDepartments() {
        Department department1 = Department.builder()
                .id(1L)
                .name("IT Department")
                .build();
        Department department2 = Department.builder()
                .id(2L)
                .name("HR Department")
                .build();
        when(departmentRepository.findAll()).thenReturn(List.of(department1, department2));

        List<DepartmentDto> result = departmentService.fetchAllDepartments();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void updateDepartment_Success() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "Updated Department");
        Department existingDepartment = Department.builder()
                .id(1L)
                .name("Old Department")
                .build();
        when(departmentRepository.findById(departmentDto.getId())).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(existingDepartment)).thenReturn(existingDepartment);

        boolean result = departmentService.updateDepartment(departmentDto);

        assertTrue(result);
        verify(departmentRepository, times(1)).save(existingDepartment);
    }

    @Test
    void updateDepartment_NotFound() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "Updated Department");

        when(departmentRepository.findById(departmentDto.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> departmentService.updateDepartment(departmentDto));
    }

    @Test
    void updateDepartment_AlreadyExists() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "Updated Department");
        Department existingDepartment = Department.builder()
                .id(1L)
                .name("Old Department")
                .build();
        Department conflictingDepartment = Department.builder()
                .id(1L)
                .name("Updated Department")
                .build();

        when(departmentRepository.findById(departmentDto.getId())).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.findByName(departmentDto.getName())).thenReturn(Optional.of(conflictingDepartment));

        assertThrows(DepartmentAlreadyExistsException.class, () -> departmentService.updateDepartment(departmentDto));
    }

    @Test
    void deleteDepartment_Success() {
        String departmentName = "IT Department";
        Department department = Department.builder()
                .id(1L)
                .name(departmentName)
                .build();

        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));

        boolean result = departmentService.deleteDepartment(departmentName);

        assertTrue(result);
        verify(departmentRepository, times(1)).deleteByName(departmentName);
    }

    @Test
    void deleteDepartment_NotFound() {
        String departmentName = "Non-Existent";

        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> departmentService.deleteDepartment(departmentName));
    }
}
