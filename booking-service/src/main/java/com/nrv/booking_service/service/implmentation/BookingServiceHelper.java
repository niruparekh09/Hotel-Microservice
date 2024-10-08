package com.nrv.booking_service.service.implmentation;

import com.nrv.booking_service.model.Booking;
import com.nrv.booking_service.request.BookingInsertionRequest;
import com.nrv.booking_service.response.BookingResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * Helper class for Room Service.
 *
 * @author Nirav Parekh
 * @see BookingServiceImpl
 * @since 1.0
 */
public class BookingServiceHelper {

    public static List<BookingResponse> bookingListMapper(List<Booking> bookingList) {
        return bookingList
                .stream()
                .map(BookingServiceHelper::getBookingResponse)
                .toList();
    }

    public static BookingResponse getBookingResponse(Booking booking) {
        if (booking == null) {
            throw new RuntimeException("Booking cannot be null");
        }

        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .roomId(booking.getRoomId())
                .customerId(booking.getCustomerId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .createdAt(booking.getCreatedAt())
                .totalPrice(booking.getTotalPrice())
                .build();
    }

    // Method to add new room
    public static Booking getBooking(BookingInsertionRequest newBookingRequest, double totalPrice) {
        if (newBookingRequest == null) {
            throw new RuntimeException("Booking cannot be null");
        }

        return Booking.builder()
                .customerId(newBookingRequest.getCustomerId())
                .roomId(newBookingRequest.getRoomId())
                .checkInDate(newBookingRequest.getCheckInDate())
                .checkOutDate(newBookingRequest.getCheckOutDate())
                .createdAt(LocalDate.now()) // We will manually add when booking is created
                .totalPrice(totalPrice)
                .build();
    }

    public static void checkUpdate(BookingInsertionRequest updateBooking, Booking existingBooking) {
        if (!updateBooking.getCustomerId().isEmpty()) {
            existingBooking.setCustomerId(updateBooking.getCustomerId());
        }
        if (!updateBooking.getRoomId().isEmpty()) {
            existingBooking.setRoomId(updateBooking.getRoomId());
        }
        if (updateBooking.getCheckInDate() != null) {
            existingBooking.setCheckInDate(updateBooking.getCheckInDate());
        }
        if (updateBooking.getCheckOutDate() != null) {
            existingBooking.setCheckOutDate(updateBooking.getCheckOutDate());
        }
    }
}
