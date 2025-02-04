package com.hahn.software.employeerecordsmanagementbackend.security.entity;

import com.hahn.software.employeerecordsmanagementbackend.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "privileges")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Privilege extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}