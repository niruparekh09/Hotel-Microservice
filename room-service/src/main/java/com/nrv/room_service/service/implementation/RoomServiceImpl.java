package com.nrv.room_service.service.implementation;

import com.nrv.room_service.request.RoomAvailabilityUpdateRequest;
import com.nrv.room_service.request.RoomInsertionRequest;
import com.nrv.room_service.request.RoomUpdateRequest;
import com.nrv.room_service.response.APIResponse;
import com.nrv.room_service.response.RoomResponse;
import com.nrv.room_service.service.RoomService;

import java.util.List;


public class RoomServiceImpl implements RoomService {
    @Override
    public List<RoomResponse> fetchAllRooms() {
        return List.of();
    }

    @Override
    public RoomResponse fetchARoom(String roomId) {
        return null;
    }

    @Override
    public RoomResponse addARoom(RoomInsertionRequest newRoom) {
        return null;
    }

    @Override
    public RoomResponse updateARoom(RoomUpdateRequest updatedRoom) {
        return null;
    }

    @Override
    public APIResponse updateRoomAvailability(RoomAvailabilityUpdateRequest updateAvailability) {
        return null;
    }

    @Override
    public APIResponse deleteRoom(String roomId) {
        return null;
    }
}
