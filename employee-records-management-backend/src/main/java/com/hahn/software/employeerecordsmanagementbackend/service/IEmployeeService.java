package com.hahn.software.employeerecordsmanagementbackend.service;

import com.hahn.software.employeerecordsmanagementbackend.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {
    /**
     * This method creates a new employee.
     *
     * @param employeeDto This is the employee data to be created.
     */
    void createEmployee(EmployeeDto employeeDto);

    /**
     * This method fetches the employee details by ID.
     *
     * @param employeeId This is the employee Id.
     *
     * @return This returns the employee details.
     */
    EmployeeDto fetchEmployee(String employeeId);

    /**
     * This method fetches the employee details.
     *
     * @return This returns the list of all employee details.
     */
    List<EmployeeDto> fetchAllEmployees();

    /**
     * This method updates the employee details.
     *
     * @param employeeDto This is the employee data to be updated.
     *
     * @return This returns true if the employee was updated, false otherwise.
     */
    boolean updateEmployee(EmployeeDto employeeDto);

    /**
     * This method deletes an employee by ID.
     *
     * @param employeeId This is the employee ID.
     *
     * @return This returns true if the employee was deleted, false otherwise.
     */
    boolean deleteEmployee(String employeeId);
}
