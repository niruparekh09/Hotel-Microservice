package com.nrv.room_service.log;
/**
 * Enum class for room log messages.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public enum RoomLogMessage {

    ROOM_LIST_GET("Get All Rooms"),
    ROOM_GET("Get a room by id: {}"),
    ROOM_ADD("Add a room by id: {}"),
    ROOM_UPDATE("Update a room by id: {}"),
    ROOM_UPDATE_AVAILABILITY("Update a room's availability by id: {}"),
    ROOM_DELETE("Delete a room by id: {}")
    ;


    private final String message;

    RoomLogMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
