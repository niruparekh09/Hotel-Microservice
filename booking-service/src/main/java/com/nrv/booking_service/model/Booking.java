package com.nrv.booking_service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Entity class for Booking. Represents the fields present in Booking database. This will
 * be used to map values from database in from of an Object.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Document(collection = "booking") // Specify the collection name in MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    private String bookingId;

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Room ID is required")
    private String roomId;

    @NotNull(message = "Check-in date is required")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    private LocalDate checkOutDate;

    @Positive(message = "Total price must be a positive value")
    private Double totalPrice;

    private LocalDate createdAt;
}
