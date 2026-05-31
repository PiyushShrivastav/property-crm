package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String mobile;

    private String email;

    private String city;

    private Double budget;

    @Column(length = 2000)
    private String remarks;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    @Enumerated(EnumType.STRING)
    private LeadSource source;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "assigned_employee_id")
    private Employee assignedEmployee;
}