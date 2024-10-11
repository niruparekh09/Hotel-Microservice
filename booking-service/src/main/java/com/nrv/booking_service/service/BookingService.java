package com.nrv.booking_service.service;

import com.nrv.booking_service.request.BookingInsertionRequest;
import com.nrv.booking_service.response.APIResponse;
import com.nrv.booking_service.response.BookingResponse;

import java.util.List;

/**
 * Service interface for Booking. It contains the abstract methods for service logic.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public interface BookingService {

    /**
     * Method to fetch all the booking present in DB.
     *
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    List<BookingResponse> fetchAllBookings();

    /**
     * Method to fetch a booking present in DB with Booking ID.
     *
     * @param bookingId Booking ID
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    BookingResponse fetchABooking(String bookingId);

    /**
     * Method to fetch a booking present in DB with Customer ID.
     *
     * @param customerId Customer ID
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    List<BookingResponse> fetchABookingByCustomer(String customerId);

    /**
     * Method to add a booking in DB.
     *
     * @param newBooking   New Booking
     * @param loggedInUser
     * @author Nirav Parekh
     * @see BookingInsertionRequest
     * @since 1.0
     */
    BookingResponse addABooking(BookingInsertionRequest newBooking, String loggedInUser);

    /**
     * Method to update a booking in DB.
     *
     * @param bookingId     Booking ID
     * @param updateBooking Updated Fields in Booking
     * @author Nirav Parekh
     * @see BookingInsertionRequest
     * @since 1.0
     */
    BookingResponse updateABooking(String bookingId, BookingInsertionRequest updateBooking);

    /**
     * Method to delete a booking in DB.
     *
     * @param bookingId Booking ID
     * @author Nirav Parekh
     * @see BookingInsertionRequest
     * @since 1.0
     */
    APIResponse deleteABooking(String bookingId);
}
