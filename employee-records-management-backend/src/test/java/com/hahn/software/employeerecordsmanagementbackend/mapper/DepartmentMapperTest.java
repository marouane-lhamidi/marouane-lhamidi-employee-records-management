package com.hahn.software.employeerecordsmanagementbackend.mapper;


import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentMapperTest {

    @Test
    void testMapToDepartmentDto() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        DepartmentDto departmentDto = new DepartmentDto();

        // Act
        DepartmentDto result = DepartmentMapper.mapToDepartmentDto(department, departmentDto);

        // Assert
        assertNotNull(result);
        assertEquals(department.getId(), result.getId());
        assertEquals(department.getName(), result.getName());
    }

    @Test
    void testMapToDepartment() {
        // Arrange
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Finance");

        Department department = new Department();

        // Act
        Department result = DepartmentMapper.mapToDepartment(departmentDto, department);

        // Assert
        assertNotNull(result);
        assertEquals(departmentDto.getId(), result.getId());
        assertEquals(departmentDto.getName(), result.getName());
    }

    @Test
    void testMapToDepartmentDtos() {
        // Arrange
        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("IT");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("Marketing");

        List<Department> departments = List.of(department1, department2);

        // Act
        List<DepartmentDto> result = DepartmentMapper.mapToDepartmentDtos(departments);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(department1.getName(), result.get(0).getName());
        assertEquals(department2.getName(), result.get(1).getName());
    }

    @Test
    void testMapToDepartments() {
        // Arrange
        DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto1.setId(1L);
        departmentDto1.setName("Operations");

        DepartmentDto departmentDto2 = new DepartmentDto();
        departmentDto2.setId(2L);
        departmentDto2.setName("Sales");

        List<DepartmentDto> departmentDtos = List.of(departmentDto1, departmentDto2);

        // Act
        List<Department> result = DepartmentMapper.mapToDepartments(departmentDtos);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(departmentDto1.getName(), result.get(0).getName());
        assertEquals(departmentDto2.getName(), result.get(1).getName());
    }
}
