package com.nrv.booking_service.service.implmentation;

import com.nrv.booking_service.model.Booking;
import com.nrv.booking_service.request.BookingInsertionRequest;
import com.nrv.booking_service.response.BookingResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
                .paymentId(booking.getPaymentId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .createdAt(booking.getCreatedAt())
                .totalPrice(booking.getTotalPrice())
                .build();
    }

    // Method to add new room
    public static Booking getBooking(BookingInsertionRequest newBookingRequest, double totalPrice, String paymentId) {
        if (newBookingRequest == null) {
            throw new RuntimeException("Booking cannot be null");
        }

        return Booking.builder()
                .customerId(newBookingRequest.getCustomerId())
                .roomId(newBookingRequest.getRoomId())
                .paymentId(paymentId)
                .checkInDate(newBookingRequest.getCheckInDate())
                .checkOutDate(newBookingRequest.getCheckOutDate())
                .createdAt(LocalDate.now()) // We will manually add when booking is created
                .totalPrice(totalPrice)
                .build();
    }

    public static void checkUpdate(BookingInsertionRequest updateBooking, Booking existingBooking) {
        Optional.ofNullable(updateBooking.getCustomerId())
                .filter(customerId -> !customerId.isEmpty())
                .ifPresent(existingBooking::setCustomerId);
        Optional.ofNullable(updateBooking.getRoomId())
                .filter(roomId -> !roomId.isEmpty())
                .ifPresent(existingBooking::setRoomId);
        Optional.ofNullable(updateBooking.getCheckInDate()).ifPresent(existingBooking::setCheckInDate);
        Optional.ofNullable(updateBooking.getCheckOutDate()).ifPresent(existingBooking::setCheckOutDate);

    }
}
