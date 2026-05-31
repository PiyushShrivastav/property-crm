package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.BookingRequest;
import com.propertycrm.app.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(
            BookingRequest request);

    BookingResponse getBooking(Long id);

    List<BookingResponse> getAllBookings();

    BookingResponse cancelBooking(Long bookingId);
}