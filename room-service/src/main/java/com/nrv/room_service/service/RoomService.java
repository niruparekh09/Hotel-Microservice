package com.nrv.room_service.service;

import com.nrv.room_service.request.RoomAvailabilityUpdateRequest;
import com.nrv.room_service.request.RoomInsertionRequest;
import com.nrv.room_service.request.RoomUpdateRequest;
import com.nrv.room_service.response.APIResponse;
import com.nrv.room_service.response.RoomResponse;

import java.util.List;

/**
 * Service interface for Room. It contains the abstract methods for service logic.
 *
 * @author Nirav Parekh
 * @see com.nrv.room_service.service.implementation.RoomServiceImpl
 * @since 1.0
 */
public interface RoomService {

    /**
     * Method to fetch all the room present in DB.
     *
     * @author Nirav Parekh
     * @see RoomResponse
     * @since 1.0
     */
    List<RoomResponse> fetchAllRooms();

    /**
     * Method to fetch a room present in DB with that ID.
     *
     * @param roomId Room id
     * @author Nirav Parekh
     * @see RoomResponse
     * @since 1.0
     */
    RoomResponse fetchARoom(String roomId);

    /**
     * Method to add a new Room.
     *
     * @param newRoom new room
     * @author Nirav Parekh
     * @see RoomInsertionRequest
     * @since 1.0
     */
    RoomResponse addARoom(RoomInsertionRequest newRoom);

    /**
     * Method to update a room.
     *
     * @param updatedRoom update room
     * @author Nirav Parekh
     * @see RoomUpdateRequest
     * @since 1.0
     */
    RoomResponse updateARoom(RoomUpdateRequest updatedRoom);

    /**
     * Method to update availability of room.
     *
     * @param updateAvailability update availability of a room
     * @author Nirav Parekh
     * @see APIResponse
     * @see RoomAvailabilityUpdateRequest
     * @since 1.0
     */
    APIResponse updateRoomAvailability(RoomAvailabilityUpdateRequest updateAvailability);

    /**
     * Method to delete a room information.
     *
     * @param roomId Room id
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    APIResponse deleteRoom(String roomId);
}
