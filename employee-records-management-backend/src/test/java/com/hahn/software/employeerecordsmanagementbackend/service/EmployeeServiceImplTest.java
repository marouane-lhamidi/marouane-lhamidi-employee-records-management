package com.hahn.software.employeerecordsmanagementbackend.service;

import com.hahn.software.employeerecordsmanagementbackend.dto.EmployeeDto;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import com.hahn.software.employeerecordsmanagementbackend.entity.Employee;
import com.hahn.software.employeerecordsmanagementbackend.exception.ResourceNotFoundException;
import com.hahn.software.employeerecordsmanagementbackend.repository.DepartmentRepository;
import com.hahn.software.employeerecordsmanagementbackend.repository.EmployeeRepository;
import com.hahn.software.employeerecordsmanagementbackend.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployee_ShouldCreateEmployeeSuccessfully() {
        // Arrange
        String email = "test@example.com";
        String departmentName = "Engineering";

        EmployeeDto employeeDto = EmployeeDto.builder()
                .email(email)
                .departmentName(departmentName)
                .build();

        Department department = new Department();
        department.setName(departmentName);

        when(employeeRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));

        // Act
        employeeService.createEmployee(employeeDto);

        // Assert
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void createEmployee_ShouldThrowExceptionIfEmailExists() {
        // Arrange
        String email = "test@example.com";

        EmployeeDto employeeDto = EmployeeDto.builder()
                .email(email)
                .build();

        when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.createEmployee(employeeDto));
    }

    @Test
    void fetchEmployee_ShouldReturnEmployeeSuccessfully() {
        // Arrange
        String employeeId = UUID.randomUUID().toString();

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.of(employee));

        // Act
        EmployeeDto result = employeeService.fetchEmployee(employeeId);

        // Assert
        assertNotNull(result);
        verify(employeeRepository, times(1)).findByEmployeeId(employeeId);
    }

    @Test
    void fetchEmployee_ShouldThrowExceptionIfEmployeeNotFound() {
        // Arrange
        String employeeId = UUID.randomUUID().toString();

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.fetchEmployee(employeeId));
    }

    @Test
    void updateEmployee_ShouldUpdateEmployeeSuccessfully() {
        // Arrange
        String employeeId = UUID.randomUUID().toString();
        String email = "test@example.com";
        String departmentName = "Engineering";

        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeeId(employeeId)
                .email(email)
                .departmentName(departmentName)
                .build();

        Employee existingEmployee = new Employee();
        Department department = new Department();

        when(employeeRepository.findByEmployeeIdAndEmail(employeeId, email))
                .thenReturn(Optional.of(existingEmployee));
        when(departmentRepository.findByName(departmentName))
                .thenReturn(Optional.of(department));

        // Act
        boolean result = employeeService.updateEmployee(employeeDto);

        // Assert
        assertTrue(result);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void deleteEmployee_ShouldDeleteEmployeeSuccessfully() {
        // Arrange
        String employeeId = UUID.randomUUID().toString();

        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeId(employeeId);

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.of(existingEmployee));

        // Act
        boolean result = employeeService.deleteEmployee(employeeId);

        // Assert
        assertTrue(result);
        verify(employeeRepository, times(1)).delete(existingEmployee);
    }

    @Test
    void deleteEmployee_ShouldThrowExceptionIfEmployeeNotFound() {
        // Arrange
        String employeeId = UUID.randomUUID().toString();

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));
    }
}
