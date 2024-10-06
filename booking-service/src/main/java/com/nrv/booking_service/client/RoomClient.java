package com.nrv.booking_service.client;

import com.nrv.booking_service.response.RoomResponse;
import com.nrv.booking_service.response.UpdateRoomAvailability;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Client Class for inter service communication with Room-Service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@FeignClient(name = "ROOM-SERVICE")
public interface RoomClient {

    @PutMapping("/availability/{id}")
    public RoomResponse updateAvailabilityOfRoom
            (@PathVariable String id, @RequestBody UpdateRoomAvailability availability);
}
