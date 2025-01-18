package com.hahn.software.employeerecordsmanagementbackend.mapper;

import com.hahn.software.employeerecordsmanagementbackend.dto.EmployeeDto;
import com.hahn.software.employeerecordsmanagementbackend.dto.enums.EmploymentStatus;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import com.hahn.software.employeerecordsmanagementbackend.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    @Test
    void testMapToEmployeeDto() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        Employee employee = Employee.builder()
                .id(1L)
                .employeeId("EMP001")
                .fullName("John Doe")
                .jobTitle("Software Engineer")
                .hireDate(new Date())
                .email("johndoe@example.com")
                .phoneNumber("1234567890")
                .address("123 Main St")
                .employmentStatus(EmploymentStatus.ACTIVE)
                .department(department)
                .build();

        EmployeeDto employeeDto = new EmployeeDto();

        // Act
        EmployeeDto result = EmployeeMapper.mapToEmployeeDto(employee, employeeDto);

        // Assert
        assertNotNull(result);
        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
        assertEquals(employee.getFullName(), result.getFullName());
        assertEquals(employee.getJobTitle(), result.getJobTitle());
        assertEquals(employee.getHireDate(), result.getHireDate());
        assertEquals(employee.getEmail(), result.getEmail());
        assertEquals(employee.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(employee.getAddress(), result.getAddress());
        assertEquals(employee.getEmploymentStatus().toString(), result.getEmploymentStatus().toString());
        assertEquals(employee.getDepartment().getName(), result.getDepartmentName());
    }

    @Test
    void testMapToEmployee() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId("EMP002");
        employeeDto.setFullName("Jane Smith");
        employeeDto.setJobTitle("HR Manager");
        employeeDto.setHireDate(new Date());
        employeeDto.setEmail("janesmith@example.com");
        employeeDto.setPhoneNumber("9876543210");
        employeeDto.setAddress("456 Elm St");
        employeeDto.setEmploymentStatus(EmploymentStatus.ACTIVE);

        Employee employee = new Employee();

        // Act
        Employee result = EmployeeMapper.mapToEmployee(employeeDto, employee, department);

        // Assert
        assertNotNull(result);
        assertEquals(employeeDto.getEmployeeId(), result.getEmployeeId());
        assertEquals(employeeDto.getFullName(), result.getFullName());
        assertEquals(employeeDto.getJobTitle(), result.getJobTitle());
        assertEquals(employeeDto.getHireDate(), result.getHireDate());
        assertEquals(employeeDto.getEmail(), result.getEmail());
        assertEquals(employeeDto.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(employeeDto.getAddress(), result.getAddress());
        assertEquals(employeeDto.getEmploymentStatus(), result.getEmploymentStatus());
        assertEquals(department, result.getDepartment());
    }

    @Test
    void testMapToEmployeeDtos() {
        // Arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Finance");

        Employee employee1 = Employee.builder()
                .id(1L)
                .employeeId("EMP003")
                .fullName("Alice Johnson")
                .jobTitle("Accountant")
                .hireDate(new Date())
                .email("alice.johnson@example.com")
                .phoneNumber("1112223333")
                .address("789 Maple St")
                .employmentStatus(EmploymentStatus.ACTIVE)
                .department(department)
                .build();

        Employee employee2 = Employee.builder()
                .id(2L)
                .employeeId("EMP004")
                .fullName("Bob Williams")
                .jobTitle("Financial Analyst")
                .hireDate(new Date())
                .email("bob.williams@example.com")
                .phoneNumber("4445556666")
                .address("123 Oak St")
                .employmentStatus(EmploymentStatus.ACTIVE)
                .department(department)
                .build();

        List<Employee> employees = List.of(employee1, employee2);

        // Act
        List<EmployeeDto> result = EmployeeMapper.mapToEmployeeDtos(employees);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(employee1.getFullName(), result.get(0).getFullName());
        assertEquals(employee2.getFullName(), result.get(1).getFullName());
        assertEquals(employee1.getDepartment().getName(), result.get(0).getDepartmentName());
        assertEquals(employee2.getDepartment().getName(), result.get(1).getDepartmentName());
    }
}
