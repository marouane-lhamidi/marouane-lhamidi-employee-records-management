package com.hahn.software.employeerecordsmanagementbackend.security.entity;

import com.hahn.software.employeerecordsmanagementbackend.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Role extends BaseEntity implements Serializable {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}