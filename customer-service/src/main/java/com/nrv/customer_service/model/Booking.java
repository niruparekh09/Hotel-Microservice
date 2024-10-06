package com.nrv.customer_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Mapping class for Booking (that is in booking service). Represents the fields present in Booking service.
 * This will be used to map values from room service to this current service.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private String bookingId;

    private String customerId;

    private String roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Double totalPrice;

    private LocalDate createdAt;
}
