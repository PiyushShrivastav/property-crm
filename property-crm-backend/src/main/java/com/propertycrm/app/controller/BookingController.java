package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.BookingRequest;
import com.propertycrm.app.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Object createBooking(
            @RequestBody BookingRequest request) {

        return bookingService.createBooking(
                request);
    }

    @GetMapping
    public Object getAllBookings() {

        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Object getBooking(
            @PathVariable Long id) {

        return bookingService.getBooking(id);
    }

    @PutMapping("/{id}/cancel")
    public Object cancelBooking(
            @PathVariable Long id) {

        return bookingService.cancelBooking(id);
    }
}