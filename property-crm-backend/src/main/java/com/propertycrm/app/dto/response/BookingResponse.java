package com.propertycrm.app.dto.response;

import com.propertycrm.app.entity.BookingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private Long id;

    private String bookingNumber;

    private String customerName;

    private String projectName;

    private String unitNo;

    private Double bookingAmount;

    private BookingStatus status;
}