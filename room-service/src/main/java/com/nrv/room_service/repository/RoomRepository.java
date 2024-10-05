package com.nrv.room_service.repository;

import com.nrv.room_service.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for Room. It connects the service to room database.
 *
 * @author Nirav Parekh
 * @see Room
 * @since 1.0
 */
public interface RoomRepository extends MongoRepository<Room, String> {
}
