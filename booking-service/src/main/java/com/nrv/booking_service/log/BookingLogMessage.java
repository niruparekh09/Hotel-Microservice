package com.nrv.booking_service.log;

/**
 * Enum class for booking log messages.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public enum BookingLogMessage {

    BOOKING_LIST_GET("Get All Bookings"),
    BOOKING_GET("Get a booking by id: {}"),
    BOOKING_GET_BY_CUSTOMER("Get a booking by customer id: {}"),
    BOOKING_ADD("Add a booking by id: {}"),
    BOOKING_UPDATE("Update a booking by id: {}"),
    BOOKING_DELETE("Delete a room by id: {}");


    private final String message;

    BookingLogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
