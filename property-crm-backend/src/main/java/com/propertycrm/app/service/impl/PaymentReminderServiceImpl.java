package com.propertycrm.app.service.impl;

import com.propertycrm.app.entity.*;
import com.propertycrm.app.repository.NotificationRepository;
import com.propertycrm.app.repository.PaymentScheduleRepository;
import com.propertycrm.app.service.PaymentReminderService;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentReminderServiceImpl
        implements PaymentReminderService {

    private final PaymentScheduleRepository
            paymentScheduleRepository;

    private final NotificationRepository
            notificationRepository;

    @Override
    @Scheduled(cron = "0 0 9 * * *")
    public void sendDueReminders() {

        LocalDate tomorrow =
                LocalDate.now().plusDays(1);

        List<PaymentSchedule> schedules =
                paymentScheduleRepository.findAll()
                        .stream()
                        .filter(s ->
                                s.getDueDate()
                                .equals(tomorrow)
                        )
                        .toList();

        for(PaymentSchedule schedule : schedules) {

            Booking booking =
                    schedule.getBooking();

            User user =
                    booking.getCustomer()
                            .getLead()
                            .getAssignedEmployee()
                            .getUser();

            Notification notification =
                    Notification.builder()
                            .title(
                                    "Payment Due Reminder")
                            .message(
                                    "Installment due tomorrow : ₹ "
                                    + schedule.getAmount())
                            .type(
                                    NotificationType.PAYMENT_DUE)
                            .createdAt(
                                    LocalDateTime.now())
                            .isRead(false)
                            .user(user)
                            .build();

            notificationRepository.save(
                    notification);
        }
    }
}