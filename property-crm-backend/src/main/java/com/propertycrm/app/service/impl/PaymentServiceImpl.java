package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.CollectionRequest;
import com.propertycrm.app.dto.request.PaymentScheduleRequest;
import com.propertycrm.app.dto.response.CollectionResponse;
import com.propertycrm.app.dto.response.PaymentScheduleResponse;
import com.propertycrm.app.entity.*;
import com.propertycrm.app.repository.*;
import com.propertycrm.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl
        implements PaymentService {

    private final BookingRepository bookingRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final CollectionRepository collectionRepository;

    @Override
    public PaymentScheduleResponse createInstallment(
            PaymentScheduleRequest request) {

        Booking booking =
                bookingRepository.findById(
                        request.getBookingId())
                .orElseThrow(() ->
                new RuntimeException("Customer not found"));

        PaymentSchedule schedule =
                PaymentSchedule.builder()
                        .booking(booking)
                        .installmentName(
                                request.getInstallmentName())
                        .amount(request.getAmount())
                        .dueDate(request.getDueDate())
                        .paidAmount(0.0)
                        .status(PaymentStatus.PENDING)
                        .build();

        schedule = paymentScheduleRepository.save(
                schedule);

        return mapSchedule(schedule);
    }

    @Override
    public List<PaymentScheduleResponse>
    getBookingSchedules(Long bookingId) {

        return paymentScheduleRepository
                .findByBookingId(bookingId)
                .stream()
                .map(this::mapSchedule)
                .toList();
    }

    @Override
    public CollectionResponse receivePayment(
            CollectionRequest request) {

        PaymentSchedule schedule =
                paymentScheduleRepository.findById(
                        request.getScheduleId())
                        .orElseThrow();

        Collection collection =
                Collection.builder()
                        .paymentSchedule(schedule)
                        .amount(request.getAmount())
                        .paymentMode(
                                request.getPaymentMode())
                        .referenceNumber(
                                request.getReferenceNumber())
                        .paymentDate(
                                LocalDateTime.now())
                        .build();

        collection = collectionRepository.save(
                collection);

        Double totalPaid =
                schedule.getPaidAmount()
                        + request.getAmount();

        schedule.setPaidAmount(totalPaid);

        if(totalPaid >= schedule.getAmount()) {

            schedule.setStatus(
                    PaymentStatus.PAID);

        } else {

            schedule.setStatus(
                    PaymentStatus.PARTIAL);
        }

        paymentScheduleRepository.save(schedule);

        return mapCollection(collection);
    }

    private PaymentScheduleResponse mapSchedule(
            PaymentSchedule schedule) {

        return PaymentScheduleResponse.builder()
                .id(schedule.getId())
                .installmentName(
                        schedule.getInstallmentName())
                .amount(schedule.getAmount())
                .paidAmount(schedule.getPaidAmount())
                .dueDate(schedule.getDueDate())
                .status(schedule.getStatus())
                .build();
    }

    private CollectionResponse mapCollection(
            Collection collection) {

        return CollectionResponse.builder()
                .id(collection.getId())
                .amount(collection.getAmount())
                .paymentMode(collection.getPaymentMode())
                .referenceNumber(
                        collection.getReferenceNumber())
                .build();
    }
}