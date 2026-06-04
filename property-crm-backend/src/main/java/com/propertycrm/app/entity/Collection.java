package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "collections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String paymentMode;

    private String referenceNumber;

    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private PaymentSchedule paymentSchedule;
}