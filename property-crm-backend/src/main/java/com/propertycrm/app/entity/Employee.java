package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String mobile;

    @Column(unique = true)
    private String email;

    private String address;

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reporting_manager_id")
    private Employee reportingManager;
}