package com.nrv.booking_service.controller;

import com.nrv.booking_service.request.BookingInsertionRequest;
import com.nrv.booking_service.response.APIResponse;
import com.nrv.booking_service.response.BookingResponse;
import com.nrv.booking_service.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Booking API. Base API {@code /api/booking}
 *
 * @author Nirav Parekh
 * @see BookingService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    BookingService service;

    /**
     * Method for {@code GET} request at {@code /api/booking}. The client will send request to get all bookings.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchAllBookings());
    }

    /**
     * Method for {@code GET} request at {@code /api/booking/{id}}. The client will send request to
     * get a booking by ID.
     *
     * @param id Booking ID
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    @GetMapping("/{id}")
    ResponseEntity<BookingResponse> getABooking(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchABooking(id));
    }

    /**
     * Method for {@code GET} request at {@code /api/booking/customer/{id}}. The client will send request to
     * get list of booking by Customer ID.
     *
     * @param id customer ID
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    @GetMapping("/customer/{id}")
    ResponseEntity<List<BookingResponse>> getBookingByCustomerId(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchABookingByCustomer(id));
    }

    /**
     * Method for {@code POST} request at {@code /api/booking}. The client will be able
     * to request to add a booking.
     *
     * @param newBooking New Booking
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see BookingResponse
     * @see BookingInsertionRequest
     * @since 1.0
     */
    @PostMapping()
    ResponseEntity<BookingResponse> addABooking(@RequestBody @Valid BookingInsertionRequest newBooking) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addABooking(newBooking));
    }

    /**
     * Method for {@code PUT} request at {@code /api/booking/{id}}. The client will be able
     * to request to update a booking.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see BookingResponse
     * @since 1.0
     */
    @PutMapping("/{id}")
    ResponseEntity<BookingResponse> updateABooking(@PathVariable String id, @RequestBody @Valid BookingInsertionRequest updateRoom) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateABooking(id, updateRoom));
    }

    /**
     * Method for {@code DELETE} request at {@code /api/booking/{id}}. The client will be able
     * to request to update a booking.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse> deleteARoom(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteABooking(id));
    }

}
