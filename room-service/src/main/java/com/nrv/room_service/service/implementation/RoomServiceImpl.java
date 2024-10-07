package com.nrv.room_service.service.implementation;

import com.nrv.room_service.exception.ResourceNotFoundException;
import com.nrv.room_service.log.RoomLogMessage;
import com.nrv.room_service.model.Room;
import com.nrv.room_service.model.enums.Availability;
import com.nrv.room_service.repository.RoomRepository;
import com.nrv.room_service.request.RoomInsertionRequest;
import com.nrv.room_service.response.APIResponse;
import com.nrv.room_service.response.RoomResponse;
import com.nrv.room_service.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nrv.room_service.service.implementation.RoomServiceHelper.*;

/**
 * Service implementation class for Room. It contains the methods for service logic.
 *
 * @author Nirav Parekh
 * @see RoomServiceHelper
 * @since 1.0
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<RoomResponse> fetchAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        List<RoomResponse> responseList = roomListMapper(roomList);
        logger.info(RoomLogMessage.ROOM_LIST_GET.getMessage());
        return responseList;
    }

    @Override
    public RoomResponse fetchARoom(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        RoomResponse response = getRoomResponse(room);
        logger.info(RoomLogMessage.ROOM_GET.getMessage(), roomId);
        return response;
    }

    @Override
    public RoomResponse addARoom(RoomInsertionRequest newRoom) {
        Room room = roomMapper(newRoom); // Mapping RoomInsertionRequest -> Room
        Room savedRoom = roomRepository.save(room); // Persisting Room
        RoomResponse response = getRoomResponse(savedRoom); // Mapping Room -> RoomResponse
        logger.info(RoomLogMessage.ROOM_ADD.getMessage(), response.getRoomId());
        return response;
    }

    @Override
    public RoomResponse updateARoom(String roomId, RoomInsertionRequest updateRoom) {
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with ID: " + roomId + " not found"));
        checkUpdate(updateRoom, existingRoom);
        Room persistedRoom = roomRepository.save(existingRoom);
        RoomResponse response = getRoomResponse(persistedRoom);
        logger.info(RoomLogMessage.ROOM_UPDATE.getMessage(), roomId);
        return response;
    }

    @Override
    public RoomResponse updateRoomAvailability(String roomId, String updateAvailability) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        room.setAvailability(Availability.valueOf(updateAvailability.toUpperCase())); // Updating room availability
        Room updatedRoom = roomRepository.save(room);
        RoomResponse response = getRoomResponse(updatedRoom);
        logger.info(RoomLogMessage.ROOM_UPDATE.getMessage(), room.getRoomId());
        return response;
    }

    @Override
    public APIResponse deleteRoom(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        roomRepository.delete(room);
        logger.info(RoomLogMessage.ROOM_DELETE.getMessage(), room.getRoomId());
        return new APIResponse("Room Deleted with id: " + room.getRoomId());
    }
}
