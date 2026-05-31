package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private Long id;
    private String customerName;
    private String mobile;
    private String email;
    private String city;
}