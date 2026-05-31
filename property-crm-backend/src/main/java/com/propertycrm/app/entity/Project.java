package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectCode;

    private String projectName;

    private String builderName;

    private String city;

    private String location;

    private Double totalArea;

    private Integer totalUnits;

    private Boolean active = true;

    private LocalDateTime createdAt;
}