package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String mobile;

    private String email;

    private String city;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;
}