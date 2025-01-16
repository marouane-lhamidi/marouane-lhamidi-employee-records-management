package com.hahn.software.employeerecordsmanagementbackend.entity;


import com.hahn.software.employeerecordsmanagementbackend.dto.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "employee")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false, unique = true)
    private String employeeId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "hire_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status", nullable = false)
    private EmploymentStatus employmentStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}