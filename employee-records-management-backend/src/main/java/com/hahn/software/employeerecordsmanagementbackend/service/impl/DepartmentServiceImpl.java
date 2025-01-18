package com.hahn.software.employeerecordsmanagementbackend.service.impl;

import com.hahn.software.employeerecordsmanagementbackend.dto.DepartmentDto;
import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import com.hahn.software.employeerecordsmanagementbackend.exception.DepartmentAlreadyExistsException;
import com.hahn.software.employeerecordsmanagementbackend.exception.ResourceNotFoundException;
import com.hahn.software.employeerecordsmanagementbackend.mapper.DepartmentMapper;
import com.hahn.software.employeerecordsmanagementbackend.repository.DepartmentRepository;
import com.hahn.software.employeerecordsmanagementbackend.service.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    DepartmentRepository departmentRepository;

    /**
     * Creates a new department.
     *
     * This method performs the following operations:
     * - Maps the provided DepartmentDto to a Department entity.
     * - Checks if a department with the given name already exists.
     * - If the department exists, throws a DepartmentAlreadyExistsException.
     * - If the department does not exist, saves the new department to the database.
     *
     * @param departmentDto The DepartmentDto object containing the department details to be created.
     * @throws DepartmentAlreadyExistsException if a department with the same name already exists.
     */
    @Override
    public void createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto, new Department());
        Optional<Department> existingDepartment = departmentRepository.findByName(department.getName());
        if (existingDepartment.isPresent()) {
            throw new DepartmentAlreadyExistsException("Department already exists with name " + department.getName());
        }
        departmentRepository.save(department);
    }

    /**
     * Fetches a department by its name.
     *
     * This method retrieves the department from the database using the provided department name.
     * If the department does not exist, a ResourceNotFoundException is thrown.
     *
     * @param departmentName The name of the department to be fetched.
     * @return DepartmentDto The DTO containing the department's details.
     * @throws ResourceNotFoundException if the department with the given name does not exist.
     */
    @Override
    public DepartmentDto fetchDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new ResourceNotFoundException("Department", "name", departmentName)
        );
        return DepartmentMapper.mapToDepartmentDto(department, DepartmentDto.builder().build());
    }

    /**
     * Fetches all departments.
     *
     * This method retrieves all departments from the database and maps them to DepartmentDto objects.
     *
     * @return List<DepartmentDto> A list of all department DTOs.
     */
    @Override
    public List<DepartmentDto> fetchAllDepartments() {
        // Retrieve all departments and map them to DTOs
        return DepartmentMapper.mapToDepartmentDtos(departmentRepository.findAll());
    }

    /**
     * Updates an existing department.
     *
     * This method performs the following operations:
     * - Verifies if the department exists using the provided department ID.
     * - Maps the provided DepartmentDto to the existing department entity.
     * - Saves the updated department entity to the database.
     *
     * @param departmentDto The DepartmentDto object containing the updated department details.
     * @return boolean Returns true if the department was updated successfully.
     * @throws ResourceNotFoundException if the department with the given ID does not exist.
     */
    @Override
    public boolean updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Department", "ID", departmentDto.getId().toString())
        );
        departmentRepository.findByName(departmentDto.getName()).ifPresent(
                dep -> {
                    throw new DepartmentAlreadyExistsException("Department already exists with name " + departmentDto.getName());
                }
        );
        DepartmentMapper.mapToDepartment(departmentDto, department);
        departmentRepository.save(department);
        return true;
    }

    /**
     * Deletes a department by its name.
     *
     * This method performs the following operations:
     * - Verifies that the department with the given name exists.
     * - Deletes the department from the database.
     *
     * @param departmentName The name of the department to be deleted.
     * @return boolean Returns true if the department was deleted successfully.
     * @throws ResourceNotFoundException if the department with the given name does not exist.
     */
    @Override
    @Transactional
    public boolean deleteDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new ResourceNotFoundException("Department", "ID", departmentName)
        );
        departmentRepository.deleteByName(department.getName());
        return true;
    }



}
