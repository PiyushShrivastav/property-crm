package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptResponse {

    private String receiptNumber;

    private String customerName;

    private Double amount;

    private String paymentMode;

    private String referenceNumber;
}