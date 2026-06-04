package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.CollectionRequest;
import com.propertycrm.app.dto.request.PaymentScheduleRequest;

public interface PaymentService {

    Object createInstallment(
            PaymentScheduleRequest request);

    Object getBookingSchedules(
            Long bookingId);

    Object receivePayment(
            CollectionRequest request);
}