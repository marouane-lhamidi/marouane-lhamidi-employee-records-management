package com.hahn.software.employeerecordsmanagementbackend.repository;

import com.hahn.software.employeerecordsmanagementbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeId(String employeeId);
    Optional<Employee> findByEmployeeIdAndEmail(String employeeId, String email);
}
