package com.nrv.room_service.controller;

import com.nrv.room_service.request.RoomInsertionRequest;
import com.nrv.room_service.request.UpdateRoomAvailability;
import com.nrv.room_service.response.APIResponse;
import com.nrv.room_service.response.RoomResponse;
import com.nrv.room_service.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Room API. Base API {@code /api/rooms}
 *
 * @author Nirav Parekh
 * @see RoomService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    RoomService service;


    /**
     * Method for {@code GET} request at {@code /api/rooms}. The client will send request to get all rooms.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see RoomResponse
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchAllRooms());
    }

    /**
     * Method for {@code GET} request at {@code /api/rooms/{id}}. The client will send request to get
     * a room.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see RoomResponse
     * @since 1.0
     */
    @GetMapping("/{id}")
    ResponseEntity<RoomResponse> getARoom(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchARoom(id));
    }

    /**
     * Method for {@code POST} request at {@code /api/rooms}. The client will be able
     * to request to add a room.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see RoomResponse
     * @see RoomInsertionRequest
     * @since 1.0
     */
    @PostMapping()
    ResponseEntity<RoomResponse> addARoom(@RequestBody @Valid RoomInsertionRequest newRoom) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addARoom(newRoom));
    }

    /**
     * Method for {@code PUT} request at {@code /api/rooms/{id}}. The client will be able
     * to request to update a room.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see RoomResponse
     * @since 1.0
     */
    @PutMapping("/{id}")
    ResponseEntity<RoomResponse> updateRoom(@PathVariable String id, @RequestBody RoomInsertionRequest updateRoom) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateARoom(id, updateRoom));
    }

    /**
     * Method for {@code PUT} request at {@code /api/rooms/availability/{id}}. The client will be able
     * to request to update availability of room.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see RoomResponse
     * @since 1.0
     */
    @PutMapping("/availability/{id}")
    ResponseEntity<RoomResponse> updateAvailabilityOfRoom(@PathVariable String id, @RequestBody UpdateRoomAvailability availability) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateRoomAvailability(id, availability.getAvailability()));
    }

    /**
     * Method for {@code DELETE} request at {@code /api/rooms/{id}}. The client will be able
     * to request to update a room.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse> deleteARoom(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteRoom(id));
    }

}
