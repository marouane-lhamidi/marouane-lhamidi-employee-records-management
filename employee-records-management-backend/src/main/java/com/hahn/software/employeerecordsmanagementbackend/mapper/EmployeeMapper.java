package com.hahn.software.employeerecordsmanagementbackend.mapper;

import com.hahn.software.employeerecordsmanagementbackend.dto.EmployeeDto;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import com.hahn.software.employeerecordsmanagementbackend.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto) {
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFullName(employee.getFullName());
        employeeDto.setJobTitle(employee.getJobTitle());
        employeeDto.setHireDate(employee.getHireDate());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setEmploymentStatus(employee.getEmploymentStatus());
        employeeDto.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null);
        return employeeDto;
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee, Department department) {
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setFullName(employeeDto.getFullName());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAddress(employeeDto.getAddress());
        employee.setEmploymentStatus(employeeDto.getEmploymentStatus());
        employee.setDepartment(department);
        return employee;
    }
    public static List<EmployeeDto> mapToEmployeeDtos(List<Employee> employees) {
        return employees.stream()
                .map(employee -> mapToEmployeeDto(employee, new EmployeeDto()))
                .collect(Collectors.toList());
    }
}
