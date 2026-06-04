package com.propertycrm.app.dto.response;

import com.propertycrm.app.entity.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PaymentScheduleResponse {

    private Long id;

    private String installmentName;

    private Double amount;

    private Double paidAmount;

    private LocalDate dueDate;

    private PaymentStatus status;
}