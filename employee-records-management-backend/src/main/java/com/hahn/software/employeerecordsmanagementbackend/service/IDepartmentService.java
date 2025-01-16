package com.hahn.software.employeerecordsmanagementbackend.service;

import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;

import java.util.List;

public interface IDepartmentService {
    /**
     * This method creates a new department.
     *
     * @param departmentDto This is the department details.
     */
    void createDepartment(DepartmentDto departmentDto);

    /**
     * This method fetches the department details by ID.
     *
     * @param departmentName This is the department Name.
     *
     * @return This returns the department details.
     */
    DepartmentDto fetchDepartment(String departmentName);

    /**
     * This method fetches all the department details.
     *
     * @return This returns a list of department details.
     */
    List<DepartmentDto> fetchAllDepartments();

    /**
     * This method updates the department details.
     *
     * @param departmentDto This is the department details.
     *
     * @return This returns a boolean indicating whether the update was successful or not.
     */
    boolean updateDepartment(DepartmentDto departmentDto);

    /**
     * This method deletes a department by ID.
     *
     * @param departmentName This is the department Name.
     *
     * @return This returns a boolean indicating whether the delete was successful or not.
     */
    boolean deleteDepartment(String departmentName);
}
