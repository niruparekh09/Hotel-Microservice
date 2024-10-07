package com.nrv.booking_service.service.implmentation;

import com.nrv.booking_service.client.RoomClient;
import com.nrv.booking_service.exception.ResourceNotFoundException;
import com.nrv.booking_service.log.BookingLogMessage;
import com.nrv.booking_service.model.Booking;
import com.nrv.booking_service.repository.BookingRepository;
import com.nrv.booking_service.request.BookingInsertionRequest;
import com.nrv.booking_service.response.APIResponse;
import com.nrv.booking_service.response.BookingResponse;
import com.nrv.booking_service.response.RoomResponse;
import com.nrv.booking_service.response.UpdateRoomAvailability;
import com.nrv.booking_service.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.nrv.booking_service.service.implmentation.BookingServiceHelper.*;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    BookingRepository repository;

    @Autowired
    RoomClient roomClient;

    @Override
    public List<BookingResponse> fetchAllBookings() {
        List<Booking> bookings = repository.findAll();
        List<BookingResponse> bookingResponseList = bookingListMapper(bookings);
        logger.info(BookingLogMessage.BOOKING_LIST_GET.getMessage());
        return bookingResponseList;
    }

    @Override
    public BookingResponse fetchABooking(String bookingId) {
        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        BookingResponse bookingResponse = getBookingResponse(booking);
        logger.info(BookingLogMessage.BOOKING_GET.getMessage(), bookingResponse.getBookingId());
        return bookingResponse;
    }

    @Override
    public List<BookingResponse> fetchABookingByCustomer(String customerId) {
        List<Booking> bookingListByCustomerId = repository.findByCustomerId(customerId);
        List<BookingResponse> bookingResponseList = bookingListMapper(bookingListByCustomerId);
        if (bookingListByCustomerId.isEmpty()) {
            logger.info("No Booking for customer with id: {}", customerId);
        } else {
            logger.info(BookingLogMessage.BOOKING_GET_BY_CUSTOMER.getMessage(),
                    bookingResponseList.get(0).getCustomerId());
        }
        return bookingResponseList;
    }

    @Override
    public BookingResponse addABooking(BookingInsertionRequest newBooking) {
        // Once we do a booking we will put Room in HOLD
        RoomResponse roomResponse = getRoomResponse(newBooking);
        int totalStay = (int) ChronoUnit.DAYS.between
                (newBooking.getCheckInDate(),
                        newBooking.getCheckOutDate());
        Booking booking = getBooking(newBooking, totalStay * roomResponse.getPricePerNight());
        repository.save(booking);
        BookingResponse bookingResponse = getBookingResponse(booking);
        logger.info(BookingLogMessage.BOOKING_ADD.getMessage(), bookingResponse.getBookingId());
        return bookingResponse;
    }

    private RoomResponse getRoomResponse(BookingInsertionRequest newBooking) {
        RoomResponse roomResponse = roomClient.updateAvailabilityOfRoom
                (newBooking.getRoomId(),
                        UpdateRoomAvailability.builder()
                                .availability("HOLD")
                                .build());
        // Once payment is over we will put Room in BOOKED
        if (true) { // Add payment client in here, Once payment is successful, this statement will be complete
            roomClient.updateAvailabilityOfRoom
                    (newBooking.getRoomId(),
                            UpdateRoomAvailability.builder()
                                    .availability("BOOKED")
                                    .build());

        }
        return roomResponse;
    }

    @Override
    public BookingResponse updateABooking(String bookingId, BookingInsertionRequest updateBooking) {
        deleteABooking(bookingId); // Deleting a booking
        BookingResponse bookingResponse = addABooking(updateBooking);// Saving the updated booking
        logger.info(BookingLogMessage.BOOKING_UPDATE.getMessage(), bookingResponse.getBookingId());
        return bookingResponse;
    }

    @Override
    public APIResponse deleteABooking(String bookingId) {
        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + bookingId));
        repository.delete(booking);
        logger.info(BookingLogMessage.ROOM_DELETE.getMessage(), booking.getBookingId());
        return new APIResponse("Booking deleted by id: " + booking.getBookingId());
    }
}
