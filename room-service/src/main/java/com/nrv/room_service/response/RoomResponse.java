package com.nrv.room_service.response;

import lombok.Data;

/**
 * Response object to provide client information about a room.
 *
 * @author Nirav Parekh
 * @see com.nrv.room_service.model.Room
 * @see com.nrv.room_service.model.enums.Type
 * @since 1.0
 */
@Data
public class RoomResponse {

    private String roomId;

    private String roomNumber;

    private String roomType;

    private Double pricePerNight;

    private Boolean available;

    private String image;

    private Integer floor;

    private String description;
}
