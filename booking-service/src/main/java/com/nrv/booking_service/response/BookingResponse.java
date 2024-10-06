package com.nrv.booking_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Response object to provide client information about a room.
 *
 * @author Nirav Parekh
 * @see com.nrv.booking_service.model.Booking
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private String bookingId;

    private String customerId;

    private String roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Double totalPrice;

    private LocalDate createdAt;
}
