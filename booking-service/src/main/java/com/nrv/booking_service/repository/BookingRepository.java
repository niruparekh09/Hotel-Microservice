package com.nrv.booking_service.repository;

import com.nrv.booking_service.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookingRepository extends MongoRepository<Booking,String> {
    List<Booking> findByCustomerId(String customerId);

    List<Booking> findByRoomId(String roomId);
}
