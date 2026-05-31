package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lead_followups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadFollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String remarks;

    private LocalDateTime nextFollowUpDate;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;
}