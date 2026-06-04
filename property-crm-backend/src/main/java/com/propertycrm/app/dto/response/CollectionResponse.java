package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionResponse {

    private Long id;

    private Double amount;

    private String paymentMode;

    private String referenceNumber;
}