package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unitNo;

    private String tower;

    private Integer floorNo;

    private Double area;

    private Double basePrice;

    private Double finalPrice;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Enumerated(EnumType.STRING)
    private UnitStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}