package com.propertycrm.app.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentScheduleRequest {

    private Long bookingId;

    private String installmentName;

    private Double amount;

    private LocalDate dueDate;
}