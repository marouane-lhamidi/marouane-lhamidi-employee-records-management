package com.hahn.software.employeerecordsmanagementbackend.security.repository;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> getUserByName(@Param("name") String name);

    @Query("SELECT r FROM Role r WHERE r.name IN (:names)")
    List<Role> getRolesByNames(@Param("names") List<String> names);

}
