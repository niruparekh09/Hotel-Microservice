package com.nrv.booking_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response object to provide client information about a room.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private String roomId;

    private String roomNumber;

    private String roomType;

    private Double pricePerNight;

    private String availability;

    private String image;

    private Integer floor;

    private String description;
}
