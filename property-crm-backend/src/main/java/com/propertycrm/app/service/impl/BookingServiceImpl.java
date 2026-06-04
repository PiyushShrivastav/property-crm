package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.BookingRequest;
import com.propertycrm.app.dto.response.BookingResponse;
import com.propertycrm.app.entity.*;
import com.propertycrm.app.repository.*;
import com.propertycrm.app.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl
        implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final ProjectRepository projectRepository;
    private final UnitRepository unitRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public BookingResponse createBooking(
            BookingRequest request) {

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId())
                .orElseThrow(() ->
                new RuntimeException("Customer not found"));

        Project project =
                projectRepository.findById(
                        request.getProjectId())
                .orElseThrow(() ->
                new RuntimeException("Project not found"));

        Unit unit =
                unitRepository.findById(
                        request.getUnitId())
                .orElseThrow(() ->
                new RuntimeException("Unit not found"));

        if(unit.getStatus() != UnitStatus.AVAILABLE) {

            throw new RuntimeException(
                    "Unit not available");
        }

        Booking booking = Booking.builder()
                .bookingNumber(
                        "BK-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0,8))
                .customer(customer)
                .project(project)
                .unit(unit)
                .bookingAmount(
                        request.getBookingAmount())
                .bookingDate(
                        LocalDateTime.now())
                .status(
                        BookingStatus.CONFIRMED)
                .build();

        booking = bookingRepository.save(
                booking);
        
        Notification notification =
                Notification.builder()
                        .title("Booking Confirmed")
                        .message(
                                "Booking created for Unit : "
                                + unit.getUnitNo())
                        .type(
                                NotificationType.BOOKING_CONFIRMED)
                        .createdAt(
                                java.time.LocalDateTime.now())
                        .isRead(false)
                        .user(customer.getLead()
                                .getAssignedEmployee()
                                .getUser())
                        .build();

        notificationRepository.save(notification);

        unit.setStatus(
                UnitStatus.BOOKED);

        unitRepository.save(unit);

        return map(booking);
    }

    @Override
    public BookingResponse getBooking(Long id) {

        return map(
                bookingRepository.findById(id)
                        .orElseThrow());
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public BookingResponse cancelBooking(
            Long bookingId) {

        Booking booking =
                bookingRepository.findById(
                        bookingId)
                        .orElseThrow();

        booking.setStatus(
                BookingStatus.CANCELLED);

        booking.getUnit().setStatus(
                UnitStatus.AVAILABLE);

        unitRepository.save(
                booking.getUnit());

        bookingRepository.save(
                booking);

        return map(booking);
    }

    private BookingResponse map(
            Booking booking) {

        return BookingResponse.builder()
                .id(booking.getId())
                .bookingNumber(
                        booking.getBookingNumber())
                .customerName(
                        booking.getCustomer()
                                .getCustomerName())
                .projectName(
                        booking.getProject()
                                .getProjectName())
                .unitNo(
                        booking.getUnit()
                                .getUnitNo())
                .bookingAmount(
                        booking.getBookingAmount())
                .status(
                        booking.getStatus())
                .build();
    }
}