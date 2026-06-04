package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "unit_holds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitHold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String mobile;

    private Double tokenAmount;

    private LocalDateTime holdTime;

    private LocalDateTime expiryTime;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}