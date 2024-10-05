package com.nrv.room_service.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Request object to get data availability of from Booking service to update
 * availability of a room.
 *
 * @author Nirav Parekh
 * @see com.nrv.room_service.model.Room
 * @since 1.0
 */
@Data
public class RoomAvailabilityUpdateRequest {
    @NotNull(message = "Room Id cannot be Null")
    private String roomId;
    @NotNull(message = "Availability status is required")
    private Boolean available;
}
