package com.hahn.software.employeerecordsmanagementbackend.repository;

import com.hahn.software.employeerecordsmanagementbackend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    void deleteByName(String name);
}
