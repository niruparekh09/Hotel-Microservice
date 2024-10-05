package com.nrv.room_service.service.implementation;

import com.nrv.room_service.model.Room;
import com.nrv.room_service.model.enums.Type;
import com.nrv.room_service.request.RoomInsertionRequest;
import com.nrv.room_service.request.RoomUpdateRequest;
import com.nrv.room_service.response.RoomResponse;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Helper class for Room Service.
 *
 * @author Nirav Parekh
 * @see com.nrv.room_service.service.implementation.RoomServiceImpl
 * @since 1.0
 */
public class RoomServiceHelper {
    public static List<RoomResponse> roomListMapper(List<Room> roomList) {
        return roomList.stream()
                .map(RoomServiceHelper::getRoomResponse)
                .toList();
    }

    public static RoomResponse getRoomResponse(Room room) {
        // Added null check for room
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        return RoomResponse.builder()
                .roomId(room.getRoomId())
                .roomType(room.getRoomType().name())
                .floor(room.getFloor())
                .image(room.getImage())
                .available(room.getAvailable())
                .roomNumber(room.getRoomNumber())
                .pricePerNight(room.getPricePerNight())
                .description(room.getDescription())
                .build();
    }

    public static Room roomMapper(RoomInsertionRequest newRoom) {
        // We are taking room type in form of string
        Type roomType = Type.valueOf(newRoom.getRoomType().toUpperCase());
        return buildRoom(newRoom.getRoomNumber(), roomType, newRoom.getImage(), newRoom.getFloor(),
                newRoom.getAvailable(), newRoom.getDescription());
    }


    // Helper method to reduce duplication in room creation logic
    private static Room buildRoom(String roomNumber, Type roomType, String image, int floor,
                                  boolean available, String description) {
        return Room.builder()
                .roomNumber(roomNumber)
                .roomType(roomType)
                .image(image)
                .floor(floor)
                .available(available)
                .pricePerNight(roomType.getPricePerNight())
                .description(description)
                .build();
    }
}
