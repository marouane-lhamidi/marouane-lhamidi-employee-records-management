package com.hahn.software.employeerecordsmanagementbackend.mapper;
import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department department, DepartmentDto departmentDto) {
        departmentDto.setName(department.getName());
        departmentDto.setId(department.getId());
        return departmentDto;
    }

    public static Department mapToDepartment(DepartmentDto departmentDto, Department department) {
        department.setName(departmentDto.getName());
        department.setId(departmentDto.getId());
        return department;
    }

    public static List<DepartmentDto> mapToDepartmentDtos(List<Department> departments) {
        return departments.stream()
                .map(department -> mapToDepartmentDto(department, new DepartmentDto()))
                .collect(Collectors.toList());
    }

    public static List<Department> mapToDepartments(List<DepartmentDto> departmentDtos) {
        return departmentDtos.stream()
                .map(departmentDto -> mapToDepartment(departmentDto, new Department()))
                .collect(Collectors.toList());
    }
}

