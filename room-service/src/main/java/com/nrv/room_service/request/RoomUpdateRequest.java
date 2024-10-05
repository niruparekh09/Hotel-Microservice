package com.nrv.room_service.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object to get data from client to update information about a room.
 *
 * @author Nirav Parekh
 * @see com.nrv.room_service.model.Room
 * @see com.nrv.room_service.model.enums.Type
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateRequest {
    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Room type is required")
    private String roomType;

    @NotNull(message = "Availability status is required")
    private String availability;

    @NotBlank(message = "Image URL is required")
    private String image;

    @Min(value = 0, message = "Floor must be non-negative")
    @Max(value = 30, message = "Floor must be less than or equal to 30")
    private Integer floor;

    @NotBlank(message = "Description is required")
    private String description;
}
