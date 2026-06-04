package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.CollectionRequest;
import com.propertycrm.app.dto.request.PaymentScheduleRequest;
import com.propertycrm.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/installment")
    public Object createInstallment(
            @RequestBody
            PaymentScheduleRequest request) {

        return paymentService
                .createInstallment(request);
    }

    @GetMapping("/booking/{bookingId}")
    public Object getSchedules(
            @PathVariable Long bookingId) {

        return paymentService
                .getBookingSchedules(bookingId);
    }

    @PostMapping("/receive")
    public Object receivePayment(
            @RequestBody
            CollectionRequest request) {

        return paymentService
                .receivePayment(request);
    }
}